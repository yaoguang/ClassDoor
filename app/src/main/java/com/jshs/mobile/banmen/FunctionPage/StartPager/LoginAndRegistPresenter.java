package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.jshs.mobile.banmen.BaseContent.BasePresenter;
import com.jshs.mobile.banmen.BaseContent.EventCenter;
import com.jshs.mobile.banmen.Events.LoginSuccessEvent;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Icezers on 2016/6/14.
 */
public class LoginAndRegistPresenter extends BasePresenter implements LoginAndRegistModel.onRequestComplete {

	LoginAndRegistView view;
	LoginAndRegistModel model;
	EventHandler eh;

	String phone;
	String password;
	String code;

	public LoginAndRegistPresenter(LoginAndRegistView view) {
		this.model = new LoginAndRegistModel();
		this.view = view;
		if (UserUtils.getInstance().isLogin()) {
			view.onLoginSuccess();
		} else {
			InitMesscodeHandler();
		}
	}

	public void onDestry() {
	}

	public void requestLogin(String username, String password) {
		if (username.length() != 11) {
			view.showToast("用户名格式错误");
			return;
		}
		if (password.length() == 0) {
			view.showToast("密码不能为空");
			return;
		}
		model.LoginRequest(username, password, this);
		view.showProgressDiaolog();
	}


	public void InitMesscodeHandler() {
		eh = new EventHandler() {

			@Override
			public void afterEvent(int event, int result, Object data) {

				if (result == SMSSDK.RESULT_COMPLETE) {
					//回调完成
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
						//提交验证码成功
						model.RegistRequest(phone, password, LoginAndRegistPresenter.this);
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						//获取验证码成功
						onRequestCodeSuccess();
					} else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {

					}
				} else {
					try {
						Throwable throwable = (Throwable) data;
						JSONObject object = new JSONObject(throwable.getMessage());
						String des = object.optString("detail");//错误描述
						int status = object.optInt("status");//错误代码
						if (status > 0 && !TextUtils.isEmpty(des)) {
							view.showToast(des);
							return;
						}
					} catch (Exception e) {
						//do something
					}
				}
			}
		};
		SMSSDK.registerEventHandler(eh); //注册短信回调
	}

	public void UnInitMesscodeHandler() {
		SMSSDK.unregisterAllEventHandler();
	}

	public void requestRegist(final String phone, final String password, String affirmPassword, String code) {
		if (phone.length() != 11) {
			view.showToast("手机号格式错误");
			return;
		}
		if (password.length() == 0) {
			view.showToast("密码不能为空");
			return;
		}
		if (!affirmPassword.equals(password)) {
			view.showToast("确认密码有误");
			return;
		}
		if (code.length() != 4) {
			view.showToast("验证码格式错误");
			return;
		}
		this.phone = phone;
		this.password = password;
		this.code = code;
		checkMessageCode(phone, code);
		view.showProgressDiaolog();
	}

	public void checkMessageCode(String phone, String code) {
		SMSSDK.submitVerificationCode("86", phone, code);
	}


	public void requestCode(String phone, String codeBtnText) {
		if (!"获取验证码".equals(codeBtnText)) {
			return;
		}
		if (phone.length() != 11) {
			view.showToast("手机号格式错误");
			return;
		}
		model.CodeRequset(phone);
		view.showProgressDiaolog();
	}

	public void requestUserInfo(String token) {
		model.UserInfoRequset(token, this);
		view.showProgressDiaolog();
	}

	@Override
	public void onRequestLoginSuccess() {
		requestUserInfo("");
	}

	@Override
	public void onRequestLoginError() {
		view.showToast("登录失败");
		view.hideProgressDiaolog();
	}

	@Override
	public void onRequestRegistSuccess(String phone, String password) {
		requestLogin(phone, password);
	}

	@Override
	public void onRequestRegistError() {
		view.showToast("注册失败");
		view.hideProgressDiaolog();
	}

	@Override
	public void onRequestUserInfoSuccess() {
		EventCenter.post(new LoginSuccessEvent());
		view.showToast("登录成功");
		view.onLoginSuccess();
		view.hideProgressDiaolog();
	}

	@Override
	public void onRequestUserInfoError() {
		view.showToast("获取用户信息失败");
		view.hideProgressDiaolog();
	}

	@Override
	public void onRequestCodeSuccess() {
		new Handler(Looper.getMainLooper()).post(new Runnable() {
			@Override
			public void run() {
				view.showToast("已发送验证码");
				view.hideProgressDiaolog();
				onCodeSend();
			}
		});
	}

	@Override
	public void onRequestCodeError() {
		view.showToast("获取验证码失败");
		view.hideProgressDiaolog();
	}


	// 倒计时
	private Handler handler = new Handler();
	private int countDownTime;

	public void onCodeSend() {
		countDownTime = 60;
		handler.post(countDown);
	}

	Runnable countDown = new Runnable() {
		@Override
		public void run() {
			if (countDownTime > 0) {
				countDownTime--;
				view.onCodeCountDown(countDownTime);
				handler.postDelayed(countDown, 1000);
			} else {
				view.onCodeCountDownComplete();
			}
		}
	};
}
