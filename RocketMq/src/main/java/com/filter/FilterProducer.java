package com.filter;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class FilterProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.1.226:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message("TopicTest11",// topic
                        "TagA",                         // tag
                        "OrderID001",                   // key
                        ("TopicTest11 Hello MetaQ: " + i).getBytes());    // body


                msg.putUserProperty("id", i + "");

                SendResult sendResult = producer.send(msg);  // orderID “10001”是传递给回调方法的自定义数据, 就是select方法中arg参数值

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();
    }
}