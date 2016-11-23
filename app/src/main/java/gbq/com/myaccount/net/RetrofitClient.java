package gbq.com.myaccount.net;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import gbq.com.myaccount.base.util.JsonUtil;
import gbq.com.myaccount.base.util.PhoneUtil;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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
class RetrofitClient {

	private static RetrofitClient mInstance;

	static RetrofitClient getInstance() {
		if (null == mInstance) {
			synchronized (NetApis.class) {
				mInstance = new RetrofitClient();
			}
		}
		return mInstance;
	}

	private OkHttpClient okHttpClient = new OkHttpClient.Builder()
			.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
			.connectionPool(new ConnectionPool(NetConfig.CONNECT_COUNT, NetConfig.CONNECT_TIME, TimeUnit.SECONDS))
			// 设置8个同时连接的个数和每个保持时间为20s
			.build();

	private Retrofit retrofit = new Retrofit.Builder()
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //添加Rxjava
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			//设置服务器ip，必须依据“/”结尾
			.baseUrl(NetConfig.URL_REL)
			.build();

	private RetrofitClientService retrofitClientService = retrofit.create(RetrofitClientService.class);

	/**
	 * post请求
	 *
	 * @param map
	 * @param listener
	 */
	void executePost(String url, HashMap<String, String> map, final HttpListener listener) {
		Call<BaseResponse> baseCall = retrofitClientService.executePost(url, map);
		map.put("token", PhoneUtil.getMac());
		retrofitClientListener = listener;
		Log.d("RetrofitClient", "requestParamsMap = " + map.toString());
		Log.d("RetrofitClient", "url = " + NetConfig.URL_REL + url);
		//noinspection unchecked
		baseCall.enqueue(callback);
	}

	void uploadImg(String url,final HttpListener listener){
		File file = new File("/storage/emulated/0/sc/share.png");
		RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
		Call<String> call = retrofitClientService.uploadImage(url,url,requestBody);
		call.enqueue(new Callback<String>() {
			@Override
			public void onResponse(Call<String> call, Response<String> response) {
				Log.v("Upload", response.message());
				Log.v("Upload", "success");
			}

			@Override
			public void onFailure(Call<String> call, Throwable t) {
				Log.e("Upload", t.toString());
			}
		});
	}

	private HttpListener retrofitClientListener;
	//返回的处理
	private Callback<BaseResponse> callback = new Callback<BaseResponse>() {
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
