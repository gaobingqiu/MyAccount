package gbq.com.myaccount.net;

import android.util.SparseArray;

/**
 * Created by yuguohe on 2016-09-10.
 */
public class NetErrorCode {
    private static final SparseArray<String> mErrorCodeArray = new SparseArray<>();
    public static String getErrmsg(int code){
        String defautmessage = "未知错误";
        return mErrorCodeArray.get(code,defautmessage);
    }
}
