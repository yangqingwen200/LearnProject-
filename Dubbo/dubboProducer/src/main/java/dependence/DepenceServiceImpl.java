package dependence;

import com.alibaba.dubbo.config.annotation.Reference;
import sample.SampleService;

/**
 * Created by wenwen on 2017/3/19.
 */
public class DepenceServiceImpl implements  DepencdService {

    @Reference
    private SampleService sampleService;

    public String dependence(String parm) {
        String build = sampleService.build("2017");
        System.out.println(build);
        return parm;
    }
}
