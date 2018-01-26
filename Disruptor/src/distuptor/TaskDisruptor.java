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

			//�󶨵���������
			dr.handleEventsWith(new TaskHandler());
		} else {
			dr = new Disruptor<>(EVENT_FACTORY, bufferSize,
					Executors.newCachedThreadPool(new DefaultThreadFactory(threadFactroyName)),
					ProducerType.MULTI, new BlockingWaitStrategy());
			dr.handleExceptionsWith(new IgnoreExceptionHandler());
			
			//�󶨶��������
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
			//���԰�ringBuffer����һ���¼����У���ônext���ǵõ�����һ���¼���
			final long sequence = ringBuffer.tryNext();
			try {
				//�����������ȡ��һ���յ��¼��������
				MsgEvent<Runnable> event = ringBuffer.get(sequence);
				event.setMsg(message);
			} finally {
				 //�����¼� 
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
