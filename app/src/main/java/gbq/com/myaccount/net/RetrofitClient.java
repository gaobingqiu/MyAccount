package gbq.com.myaccount.net;

import android.util.Log;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import gbq.com.myaccount.module.main.User;
import gbq.com.myaccount.module.news.entity.News;
import gbq.com.myaccount.module.register.RegisterResponseVo;
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
public class RetrofitClient {

	private static RetrofitClient mInstance;

	public static RetrofitClient getInstance() {
		if (null == mInstance) {
			synchronized (RetrofitClient.class) {
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
	 */
	public void userLogin(String userName, String password, Callback<BaseResponse<User>> call) {
		Call<BaseResponse<User>> baseCall = retrofitClientService.userLogin(userName, password);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void register(final String username, final String password, String tel, String code, Callback<BaseResponse<RegisterResponseVo>> call) {
		Call<BaseResponse<RegisterResponseVo>> baseCall = retrofitClientService.register(username, password, tel, code);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void quickRegister(String userName, Callback<BaseResponse<RegisterResponseVo>> call) {
		Call<BaseResponse<RegisterResponseVo>> baseCall = retrofitClientService.quickRegister(userName);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void getUser(String userName, Callback<BaseResponse<User>> call) {
		Call<BaseResponse<User>> baseCall = retrofitClientService.getUser(userName);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void getCode(String tel, Callback<BaseResponse<String>> call) {
		Call<BaseResponse<String>> baseCall = retrofitClientService.getCode(tel);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void logout(String userName, Callback<BaseResponse<String>> call) {
		Call<BaseResponse<String>> baseCall = retrofitClientService.logout(userName);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void modifyPassword(String userName, String oldPassword, String newPassword, Callback<BaseResponse<User>> call) {
		Call<BaseResponse<User>> baseCall = retrofitClientService.reset(userName, oldPassword, newPassword);
		baseCall.enqueue(call);
	}

	/**
	 * post请求
	 */
	public void getNews(String type, int pageIndex, Callback<BaseResponse<List<News>>> call) {
		Call<BaseResponse<List<News>>> baseCall = retrofitClientService.getNews(type, pageIndex);
		baseCall.enqueue(call);
	}

	void uploadImg(String url, final HttpListener listener) {
		File file = new File("/storage/emulated/0/sc/share.png");
		RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
		Call<String> call = retrofitClientService.uploadImage(url, url, requestBody);
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
}
