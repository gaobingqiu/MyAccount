package gbq.com.myaccount;

import android.app.Application;

/**
 * 代表着这个应用
 * Created by gbq on 2016-12-6.
 */

public class MyApplication extends Application {
	private static MyApplication instance;

	public static MyApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}
}
