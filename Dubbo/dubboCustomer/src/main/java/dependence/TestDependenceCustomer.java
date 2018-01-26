package dependence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wenwen on 2017/3/18.
 */
public class TestDependenceCustomer {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext_dependence.xml"});

        context.start();
        DepencdService demoService = (DepencdService) context.getBean("dependenceService"); // 获取bean

        String message = "";
        try {
            message = demoService.dependence("dependence");
            System.out.println(" the dependence from server is:" + message);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
