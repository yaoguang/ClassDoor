package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Handler;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.BasePresenter;
import com.jshs.mobile.banmen.BaseContent.EventCenter;
import com.jshs.mobile.banmen.Events.LoginSuccessEvent;

/**
 * Created by Icezers on 2016/6/14.
 */
public class LoginAndRegistPresenter extends BasePresenter implements LoginAndRegistModel.onRequestComplete {

    LoginAndRegistView view;
    LoginAndRegistModel model;

    public LoginAndRegistPresenter(LoginAndRegistView view) {
        this.model = new LoginAndRegistModel();
        this.view = view;
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

    public void requestRegist(final String phone, final String password, String affirmPassword, String code) {
        if (phone.length() != 11) {
            view.showToast("手机号格式错误");
            return;
        }
        if (password.length() == 0) {
            view.showToast("密码不能为空");
            return;
        }
        if (affirmPassword.equals(password)) {
            view.showToast("确认密码有误");
            return;
        }
        if (code.length() != 4) {
            view.showToast("验证码格式错误");
            return;
        }
        model.RegistRequest(phone, password, code, this);
        view.showProgressDiaolog();
    }

    public void requestCode(String phone, String codeBtnText) {
        if (!"获取验证码".equals(codeBtnText)) {
            return;
        }
        if (phone.length() != 11) {
            view.showToast("手机号格式错误");
            return;
        }
        model.CodeRequset(phone, this);
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
        view.hideProgressDiaolog();
    }

    @Override
    public void onRequestUserInfoError() {
        view.showToast("获取用户信息失败");
        view.hideProgressDiaolog();
    }

    @Override
    public void onRequestCodeSuccess() {
        view.showToast("已发送验证码");
        view.hideProgressDiaolog();
        onCodeSend();
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
