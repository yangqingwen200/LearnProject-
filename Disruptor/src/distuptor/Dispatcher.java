package distuptor;


/**
 * Date: 14-12-15
 *
 * @author jc.feng
 * @version V1.0
 */
public interface Dispatcher {

	public boolean dispatch(Runnable message);

	public void shutdown();
}

