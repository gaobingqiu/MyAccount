package gbq.com.myaccount.net;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * 网络请求
 * Created by gbq on 2016-10-4.
 */

public interface RetrofitClientService {
    @FormUrlEncoded
    @POST
    Call<BaseResponse> executePost(@Url String url, @FieldMap HashMap<String, String> map);
}
