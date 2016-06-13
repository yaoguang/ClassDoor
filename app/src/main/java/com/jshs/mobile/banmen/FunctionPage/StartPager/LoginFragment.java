package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Start.LoginRequest;
import com.jshs.mobile.banmen.Models.UserModel;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by SZH on 2016/6/9.
 */
@ContentView(R.layout.login_fragment)
public class LoginFragment extends XBaseFragment {
    @ViewInject(R.id.username)
    private EditText username;
    @ViewInject(R.id.password)
    private EditText password;
    @ViewInject(R.id.login)
    private TextView login;
    @ViewInject(R.id.forget_password)
    private TextView forgetPassword;
    @ViewInject(R.id.qq_login)
    private TextView qqLogin;
    @ViewInject(R.id.weixin_login)
    private TextView weixinLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return getContent();
    }

    @Event(value = {R.id.login, R.id.forget_password, R.id.qq_login, R.id.weixin_login})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.forget_password:
                break;
            case R.id.qq_login:
                break;
            case R.id.weixin_login:
                break;
        }
    }

    private void login() {
        if (username.length() != 11) {
            Toast.makeText(getActivity(), "用户名格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        requestLogin();
    }

    private void requestLogin() {
        AsyncHttp.getInstance().addRequest(new LoginRequest(username.getText().toString(), password.getText().toString(),
                new Response.Listener<UserModel>() {
                    @Override
                    public void onResponse(UserModel response) {
                        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                    }
                },
                new CodeErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
