package com.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;


/**
 * 发送事务消息例子
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {

        TransactionMQProducer producer = new TransactionMQProducer("TransactionProducer");
        // 事务回查最小并发数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);
        producer.setNamesrvAddr("192.168.1.226:9876");

        TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
        producer.setTransactionCheckListener(transactionCheckListener);

        TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message("TopicTest", "TagA", "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes());
                TransactionSendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, "566");

                //这句话打印, 只有等tranExecuter 执行完毕了, 才会打印
                System.out.println("Hello RocketMQ " + i);

                //TimeUnit.SECONDS.sleep(1);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }

       /* for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }*/

        producer.shutdown();

    }
}