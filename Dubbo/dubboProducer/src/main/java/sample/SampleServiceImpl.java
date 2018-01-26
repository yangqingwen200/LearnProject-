package sample;

/**
 * Created by wenwen on 2017/3/18.
 */
public class SampleServiceImpl implements SampleService {

    public String build(String name) {
        return "Hello: " + name;
    }
}
