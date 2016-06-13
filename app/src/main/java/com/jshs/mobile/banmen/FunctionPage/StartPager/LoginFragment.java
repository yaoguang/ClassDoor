package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
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

    private LoginAndRegistPresenter presenter;

    public LoginFragment(LoginAndRegistPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

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
        presenter.requestLogin(username.getText().toString(), password.getText().toString());
    }
}
