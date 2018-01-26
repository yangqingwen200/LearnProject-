package com.filter;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.MixAll;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class FilterConsumer {

    public static void main(String[] args) throws InterruptedException,
            MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        consumer.setNamesrvAddr("192.168.1.226:9876");

        //Filter类所在磁盘位置
        //Filter类不能包含中文, 否则 打印 filterCode 为null
        String filterCode = MixAll.file2String("D:\\Workspaceidea\\RocketMq\\src\\main\\java\\com\\filter\\MessageFilterImpl.java");
        System.out.println(filterCode);

        //com.filter.MessageFilterImpl Filter类名
        consumer.subscribe("TopicTest11", "com.filter.MessageFilterImpl", filterCode);

        // consumer.subscribe("TopicTest11", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

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