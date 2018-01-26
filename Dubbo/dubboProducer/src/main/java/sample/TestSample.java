package sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenwen on 2017/3/18.
 */
public class TestSample {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext_sample.xml"});
        context.start();

        System.out.println("Sample Run....");

        System.in.read(); // 按任意键退出
    }

}
