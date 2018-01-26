package distuptor.event;


/**
 * Date: 14-12-15
 *
 * @author jc.feng
 * @version V1.0
 */
public class MsgEvent<T> {

	private T msg;

	public T getMsg() {
		return msg;
	}

	public void setMsg(T msg) {
		this.msg = msg;
	}
}
