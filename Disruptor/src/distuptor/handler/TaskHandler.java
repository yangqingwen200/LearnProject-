package distuptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.WorkHandler;

import distuptor.event.MsgEvent;

/**
 * Date: 14-12-15
 *
 * @author jc.feng
 * @version V1.0
 */
public class TaskHandler implements EventHandler<MsgEvent<Runnable>>, WorkHandler<MsgEvent<Runnable>>, LifecycleAware {

	@Override
	public void onEvent(MsgEvent<Runnable> msgEvent, long l, boolean b) throws Exception {
		msgEvent.getMsg().run();
	}

	@Override
	public void onStart() {
		//TaskDisruptor PAY_TASK_DISRUPTOR = new TaskDisruptor(32, "CustomServerPay dispatcher") 打印32次
		System.out.println("Task handler on start: " + Thread.currentThread().getName());
	}

	@Override
	public void onShutdown() {
		// PAY_TASK_DISRUPTOR.shutdown() 打印32次
		System.out.println("Task handler on shutdown: " + Thread.currentThread().getName());
	}

	@Override
	public void onEvent(MsgEvent<Runnable> msgEvent) throws Exception {
		msgEvent.getMsg().run();
	}
}
