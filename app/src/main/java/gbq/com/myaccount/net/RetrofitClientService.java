package gbq.com.myaccount.net;


import java.util.List;

import gbq.com.myaccount.model.BaseResponse;
import gbq.com.myaccount.model.User;
import gbq.com.myaccount.model.News;
import gbq.com.myaccount.model.RegisterResponseVo;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 网络请求
 * Created by gbq on 2016-10-4.
 */
interface RetrofitClientService {
	@POST("loginInterface/login.do")
	Call<BaseResponse<User>> userLogin(@Query("userName") String userName, @Query("password") String password);

	@POST("loginInterface/register.do")
	Call<BaseResponse<RegisterResponseVo>> register(@Query("userName") String username, @Query("password") String password,
													@Query("tel") String tel, @Query("code") String code);

	@POST("loginInterface/quickRegister.do")
	Call<BaseResponse<RegisterResponseVo>> quickRegister(@Query("userName") String userName);

	@POST("loginInterface/getCode.do")
	Call<BaseResponse<String>> getCode(@Query("tel") String tel);

	@POST("loginInterface/getUser.do")
	Call<BaseResponse<User>> getUser(@Query("userName") String userName);

	@POST("loginInterface/logout.do")
	Call<BaseResponse<String>> logout(@Query("userName") String userName);

	@POST("loginInterface/reset.do")
	Call<BaseResponse<User>> reset(@Query("userName") String userName, @Query("oldPassword") String oldPassword,
								   @Query("newPassword") String newPassword);

	@POST("newsInterface/getNews.do")
	Call<BaseResponse<List<News>>> getNews(@Query("type") String type, @Query("pageIndex") int pageIndex);

	@Multipart
	@POST
	Call<String> uploadImage(@Url String url, @Part("fileName") String description,
							 @Part("file\"; filename=\"image.png\"") RequestBody imgs);

}
