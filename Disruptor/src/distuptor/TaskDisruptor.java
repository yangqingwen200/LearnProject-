package distuptor;


import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.InsufficientCapacityException;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import distuptor.event.MsgEvent;
import distuptor.handler.TaskHandler;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Date: 14-12-15
 *
 * @author jc.feng
 * @version V1.0
 */
public final class TaskDisruptor implements Dispatcher {

	private final static EventFactory<MsgEvent<Runnable>> EVENT_FACTORY = MsgEvent::new;

	private Disruptor<MsgEvent<Runnable>> disruptor;

	public TaskDisruptor(final int workNum) {
		this(workNum, "TaskDisruptor");
	}

	public TaskDisruptor(final int workNum, String threadFactoryName) {
		this(workNum, threadFactoryName, 128);
	}

	@SuppressWarnings("unchecked")
	public TaskDisruptor(final int workNum, String threadFactroyName, int bufferSize) {
		if (bufferSize < 0 || (bufferSize & bufferSize - 1) != 0) {
			throw new IllegalArgumentException("bufSize must be a power of 2");
		}
		Disruptor<MsgEvent<Runnable>> dr;
		if (workNum == 1) {
			dr = new Disruptor<>(EVENT_FACTORY, bufferSize,
					Executors.newSingleThreadExecutor(new DefaultThreadFactory(threadFactroyName)),
					ProducerType.MULTI, new BlockingWaitStrategy());
			dr.handleExceptionsWith(new IgnoreExceptionHandler());

			//绑定单个消费者
			dr.handleEventsWith(new TaskHandler());
		} else {
			dr = new Disruptor<>(EVENT_FACTORY, bufferSize,
					Executors.newCachedThreadPool(new DefaultThreadFactory(threadFactroyName)),
					ProducerType.MULTI, new BlockingWaitStrategy());
			dr.handleExceptionsWith(new IgnoreExceptionHandler());
			
			//绑定多个消费者
			WorkHandler<MsgEvent<Runnable>>[] handlers = new TaskHandler[workNum];
			for (int i = 0; i < workNum; i++) {
				handlers[i] = new TaskHandler();
			}
			dr.handleEventsWithWorkerPool(handlers);
		}
		dr.start();
		disruptor = dr;
	}

	@Override
	public boolean dispatch(Runnable message) {
		RingBuffer<MsgEvent<Runnable>> ringBuffer = disruptor.getRingBuffer();
		try {
			//可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
			final long sequence = ringBuffer.tryNext();
			try {
				//用上面的索引取出一个空的事件用于填充
				MsgEvent<Runnable> event = ringBuffer.get(sequence);
				event.setMsg(message);
			} finally {
				 //发布事件 
				ringBuffer.publish(sequence);
			}
			return true;
		} catch (InsufficientCapacityException e) {
			return false;
		}
	}

	@Override
	public void shutdown() {
		if (disruptor != null) {
			disruptor.shutdown();
		}
	}
}
