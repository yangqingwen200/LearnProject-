package com.orderlycustomer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;

import java.util.List;

public class OrderlyProducer {

    public static void main(String[] args) throws MQClientException,
            InterruptedException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.1.226:9876");
        producer.setInstanceName("FilterProducer");

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();

        /**
         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
         */
        for (int i = 0; i < 11; i++) {
            try {
                {
                    Message msg = new Message("TopicTest11",// topic
                            "TagA",                         // tag
                            "OrderID001",                   // key
                            ("TopicTest11 Hello MetaQ: " + i).getBytes());    // body

                    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            int id = Integer.parseInt(arg.toString()); //10001
                            int size = mqs.size(); //从namesrv 获取的所有队列
                            int index = id % size; //如果队列数不变，同一个订单号取到的队列是同一个
                            return mqs.get(index);
                        }
                    }, 10001);  // orderID “10001”是传递给回调方法的自定义数据, 就是select方法中arg参数值

                    //System.out.println(sendResult);
                    //System.out.println("TopicTest11 Hello MetaQ: " + i);
                }

               /* {
                    Message msg = new Message("TopicTest11",// topic
                            "TagB",                         // tag
                            "OrderID002",                   // key
                            ("TopicTest12 Hello MetaQ: " + i).getBytes());    // body
                   // SendResult sendResult = producer.send(msg);
                    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            Integer index = (Integer) arg;
                            return mqs.get(index);
                        }
                    }, 0);
                    //System.out.println(sendResult);
                }*/

/*
                {
                    Message msg = new Message("TopicTest13",// topic
                            "TagC",                         // tag
                            "OrderID003",                   // key
                            ("TopicTest13 Hello MetaQ: " + i).getBytes());    // body
                    SendResult sendResult = producer.send(msg);
                    System.out.println(sendResult);
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TimeUnit.MILLISECONDS.sleep(1000);
        }

        /**
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
         */
        producer.shutdown();
    }
}