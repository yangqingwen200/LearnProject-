package com.normlycustomer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class NormlyConsumer {

    /**
     * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
     * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
     */
    public static void main(String[] args) throws InterruptedException,
            MQClientException {
        /**
         * Consumer组名，多个Consumer如果属于一个应用，订阅同样的消息，且消费逻辑一致，则应该将它们归为同一组
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        consumer.setNamesrvAddr("192.168.1.226:9876");
        consumer.setInstanceName("Consumber");

        /**
         * 订阅指定topic下tags分别等于TagA或TagB或TagC
         */
        consumer.subscribe("TopicTest11", "*");
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic
         */
        /*consumer.subscribe("TopicTest12", "*");*/

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**
             * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
             * consumeThreadMin:消费线程池数量 默认最小值10
             * consumeThreadMax:消费线程池数量 默认最大值20
             */
            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                for (MessageExt msg : msgs) {
                    System.out.println(msg.getTopic() + ": " + msg.getTags() + ": " + new String(msg.getBody()) + ": " + msg.getQueueId());
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();

        System.out.println("FilterConsumer Started.");

    }
}