package com.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 未决事务，服务器回查客户端，broker端发起请求代码没有被调用，所以此处代码可能没用。<br>
 * 当生产者发送第二条消息时, MQ服务器始终没有收到消息, MQ服务器会自动检查消息中处于prepare状态的事务消息。<br>
 * 就是调用此类中checkLocalTransactionState方法。
 *
 * RocketMQ 3.2.6把这个回调的逻辑给阉割掉了, 此功能需要自己实现
 *
 * 思路: 可以写定时任务,
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
    private AtomicInteger transactionIndex = new AtomicInteger(0);


    public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
        System.out.println("server checking TrMsg " + msg.toString());

        int value = transactionIndex.getAndIncrement();

        /*
          生产时发送的msg, 会传递回来
          根据自己的业务逻辑, 返回rollback或者commit
         */
        try {
            if ((value % 6) == 0) {
                throw new RuntimeException("Could not find db");
            } else if ((value % 4) == 0) {
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        } catch (RuntimeException e) {

            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        return LocalTransactionState.UNKNOW;
    }
}