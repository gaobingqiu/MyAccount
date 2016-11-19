package gbq.com.myaccount.net;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.base.util.PhoneUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static gbq.com.myaccount.net.NetConfig.DEFAULT_TIMEOUT;

/**
 * 网络请求
 * Created by gbq on 2016-10-4.
 */
public class RetrofitClient {

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(NetConfig.CONNECT_COUNT, NetConfig.CONNECT_TIME, TimeUnit.SECONDS))
            // 设置8个同时连接的个数和每个保持时间为20s
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加Rxjava
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            //设置服务器ip，必须依据“/”结尾
            .baseUrl(NetConfig.URL_REL)
            .build();

    private static RetrofitClientService retrofitClientService = retrofit.create(RetrofitClientService.class);

    /**
     * post请求
     *
     * @param map
     * @param listener
     */
    public static void executePost(String url, HashMap<String, String> map, final HttpListener listener) {
        Call<BaseResponse> baseCall = retrofitClientService.executePost(url,map);
        map.put("token", PhoneUtil.getMac());
        retrofitClientListener = listener;
        Log.d("RetrofitClient", "requestParamsMap = "+map.toString());
        Log.d("RetrofitClient", "url = "+NetConfig.URL_REL+url);
        //noinspection unchecked
        baseCall.enqueue(callback);
    }

    private static HttpListener retrofitClientListener;
    //返回的处理
    private static Callback<BaseResponse> callback = new Callback<BaseResponse>() {
        @Override
        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
            if (response.isSuccess()) {
                BaseResponse baseResponse = response.body();
                if (baseResponse.isOk()) {
                    try {
                        String result = JsonUtil.createJsonString(baseResponse.getData());
                        retrofitClientListener.onSuccess(result);
                        Log.d("RetrofitClient", "isSuccess=" + baseResponse.getCode() + result);
                    } catch (Exception e) {
                        retrofitClientListener.onFail();
                    }
                } else {
                    String errorMessage = NetErrorCode.getErrmsg(baseResponse.getCode());
                    Log.d("RetrofitClient", "onError=" + baseResponse.getCode() + errorMessage);
                    retrofitClientListener.onError(baseResponse.getCode(), errorMessage);
                }
            } else {
                retrofitClientListener.onFail();
            }
        }

        @Override
        public void onFailure(Call<BaseResponse> call, Throwable t) {
            Log.d("RetrofitClient", "onFailure=" + "Net failure!");
            retrofitClientListener.onFail();
        }

    };
}
