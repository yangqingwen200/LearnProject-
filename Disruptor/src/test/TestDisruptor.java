package test;

import distuptor.TaskDisruptor;

public class TestDisruptor {

	protected final static TaskDisruptor PAY_TASK_DISRUPTOR = new TaskDisruptor(32, "CustomServerPay dispatcher");

	public static void main(String[] args) {
		/*DisruptorThread dt = new DisruptorThread();
		
		PAY_TASK_DISRUPTOR.dispatch(dt);
		
		PAY_TASK_DISRUPTOR.shutdown();*/
	}
	
}
