package gbq.com.myaccount.net;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import gbq.com.myaccount.base.util.JsonUtil;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static gbq.com.myaccount.net.NetConfig.DEFAULT_TIMEOUT;

/**
 * 网络请求
 * Created by gbq on 2016-10-4.
 */
public class RetrofitClient {
    //最终的请求参数
    private static HashMap<String, String> requestParamsMap;

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(NetConfig.CONETION_COUNT, NetConfig.CONETION_TIME, TimeUnit.SECONDS))
            // 设置8个同时连接的个数和每个保持时间为20s
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
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
        setRequestMap(map);
        Call<BaseResponse> baseCall = retrofitClientService.executePost(url,requestParamsMap);
        retrofitClientListener = listener;
        Log.d("RetrofitClient", "requestParamsMap = "+requestParamsMap.toString());
        Log.d("RetrofitClient", "url = "+NetConfig.URL_REL+url);
        //noinspection unchecked
        baseCall.enqueue(callback);
    }

    /**
     * 根据固定参数和不定参数生成签名，组合成最后的请求参数
     *
     * @param map
     */
    private static void setRequestMap(Map<String, String> map) {
        requestParamsMap = new HashMap<String, String>();
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            requestParamsMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        //设置基本的请求参数----------begin--------------
        if (!TextUtils.isEmpty(NetConfig.Token)) {
            requestParamsMap.put("access_token", NetConfig.Token);
        }
        if (NetConfig.UId != 0) {
            requestParamsMap.put("uid", String.valueOf(NetConfig.UId));
        }
        requestParamsMap.put("appid", String.valueOf(NetConfig.appId));
        requestParamsMap.put("time", String.valueOf(System.currentTimeMillis() / 1000));
        //设置基本的请求参数----------end--------------
        //获取签名
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
