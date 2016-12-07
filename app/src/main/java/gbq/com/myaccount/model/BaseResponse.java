package gbq.com.myaccount.model;


/**
 * 网络返回基类 支持泛型
 * Created by gbq
 */
@SuppressWarnings("unused")
public class BaseResponse<T> {

	private int code = -1;
	private String msg;
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}


	public T getData() {
		return data;
	}


	public boolean isOk() {
		return code == 0;
	}

}
