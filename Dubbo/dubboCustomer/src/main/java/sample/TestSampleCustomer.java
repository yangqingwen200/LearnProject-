package sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenwen on 2017/3/18.
 */
public class TestSampleCustomer {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext_sample.xml"});
        context.start();
        SampleService demoService = (SampleService) context.getBean("demoService"); // 获取bean

        String message = "";
        try {
            message = demoService.build("2016-10-20");
            System.out.println(" the sample from server is:" + message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
