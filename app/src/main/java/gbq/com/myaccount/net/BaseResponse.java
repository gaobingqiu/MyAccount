package gbq.com.myaccount.net;


import com.google.gson.JsonObject;

/**
 * 网络返回基类 支持泛型
 * Created by gbq
 */
public class BaseResponse {

    private int code = -1;
    private String msg;
    private JsonObject data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    public boolean isOk() {
        return code == 0;
    }

}
