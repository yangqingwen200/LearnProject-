package dependence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenwen on 2017/3/18.
 */
public class TestDependence {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext_dependence.xml"});
        context.start();

        System.out.println("Dependence Run....");

        System.in.read(); // 按任意键退出
    }

}
