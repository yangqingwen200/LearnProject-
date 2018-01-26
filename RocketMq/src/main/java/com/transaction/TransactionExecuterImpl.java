package com.transaction;

import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.common.message.Message;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 执行本地事务
 */
public class TransactionExecuterImpl implements LocalTransactionExecuter {
    private AtomicInteger transactionIndex = new AtomicInteger(1);


    //只有生产者发送消费给MQ服务器, MQ服务商响应后, 此方法才会开始执行
    //可以理解为这个方法是MQ服务器自动回调的
    public LocalTransactionState executeLocalTransactionBranch(final Message msg, final Object arg) {

        //在Producer类 producer.sendMessageInTransaction三个参数值
       // System.out.println(arg);
        System.out.println("TransactionExecuterImpl: " + new String(msg.getBody()));
        int value = transactionIndex.getAndIncrement();

        try {
            if (value == 0) {
                throw new RuntimeException("Could not find db");
            } else if (value == 3) {
                return LocalTransactionState.COMMIT_MESSAGE;
            }

        } catch (RuntimeException e) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }

        //UNKNOW状态 为不成功, 也不回滚, 就是发第二条确认消息时, 生产者跟MQ服务器断开了, MQ服务器会自动检查消息中处于prepare状态的事务消息。<br>
        // 调用TransactionCheckListenerImpl类中checkLocalTransactionState方法, RocketMQ 3.2.6把这款回调的逻辑给阉割掉了
        //UNKNOW并没有了, 现在只是模拟无法成功发送第二条信息的场景
        return LocalTransactionState.UNKNOW;

    }
}