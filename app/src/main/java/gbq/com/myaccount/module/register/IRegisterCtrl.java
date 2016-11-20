package gbq.com.myaccount.module.register;

import gbq.com.myaccount.base.IBaseCtrl;

/**
 * 这个是注册活动的控制方法
 * Created by gbq on 2016-11-12.
 */

interface IRegisterCtrl extends IBaseCtrl {
	void toRegisterSuccessActivity(String userName, String password);
}
