package com.jshs.mobile.banmen.FunctionPage.StartPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.BaseManager;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Start.GetUserInfoRequest;
import com.jshs.mobile.banmen.Http.Start.LoginRequest;
import com.jshs.mobile.banmen.Http.Start.RegistRequest;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;

import cn.smssdk.SMSSDK;

/**
 * Created by Icezers on 2016/6/14.
 */
public class LoginAndRegistModel extends BaseManager {

	public void LoginRequest(String username, String password, final onRequestComplete listener) {
		AsyncHttp.getInstance().addRequest(new LoginRequest(username, password,
				new Response.Listener<User>() {
					@Override
					public void onResponse(User response) {
						UserUtils.getInstance().updateUser(response);
						if (listener != null) {
							listener.onRequestLoginSuccess();
						}
					}
				},
				new CodeErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						super.onErrorResponse(error);
						if (listener != null) {
							listener.onRequestLoginError();
						}
					}
				}));
	}

	public void RegistRequest(final String phone, final String password, String code, final onRequestComplete listener) {
		AsyncHttp.getInstance().addRequest(new RegistRequest(phone, password, code,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if (listener != null) {
							listener.onRequestRegistSuccess(phone, password);
						}
					}
				},
				new CodeErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						super.onErrorResponse(error);
						if (listener != null) {
							listener.onRequestRegistError();
						}
					}
				}));
	}

	public void CodeRequset(String phone) {
		SMSSDK.getVerificationCode("86", phone);
	}

	public void UserInfoRequset(String token, final onRequestComplete listener) {
		AsyncHttp.getInstance().addRequest(new GetUserInfoRequest(new Response.Listener<User>() {
			@Override
			public void onResponse(User response) {
				UserUtils.getInstance().updateUser(response);
				if (listener != null) {
					listener.onRequestUserInfoSuccess();
				}
			}
		}, new CodeErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				super.onErrorResponse(error);
				if (listener != null) {
					listener.onRequestUserInfoError();
				}
			}
		}));
	}


	public interface onRequestComplete {
		void onRequestRegistSuccess(String phone, String password);

		void onRequestRegistError();

		void onRequestLoginSuccess();

		void onRequestLoginError();

		void onRequestUserInfoSuccess();

		void onRequestUserInfoError();

		void onRequestCodeSuccess();

		void onRequestCodeError();
	}

}
