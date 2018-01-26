package com.filter;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class FilterConsumer1 {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        consumer.setNamesrvAddr("192.168.1.226:9876");
        consumer.setInstanceName("Consumber");

        consumer.subscribe("TopicTest11", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**
             * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
             * consumeThreadMin:消费线程池数量 默认最小值10
             * consumeThreadMax:消费线程池数量 默认最大值20
             */
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                for (MessageExt msg : msgs) {
                    System.out.println("Topic: " + msg.getTopic() + ", tags: " + msg.getTags() + ", body: " + new String(msg.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.println("FilterConsumer Started.");

    }
}