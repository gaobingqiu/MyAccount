package gbq.com.myaccount.net;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 网络请求
 * Created by gbq on 2016-10-4.
 */
interface RetrofitClientService {
	@FormUrlEncoded
	@POST
	Call<BaseResponse> executePost(@Url String url, @FieldMap HashMap<String, String> map);

	@GET
	Observable<ResponseBody> downloadImage(@Url String fileUrl);
}
