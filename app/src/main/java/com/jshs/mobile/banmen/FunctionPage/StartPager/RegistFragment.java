package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jshs.mobile.banmen.BaseContent.EventCenter;
import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by SZH on 2016/6/9.
 */
@ContentView(R.layout.regist_fragment)
public class RegistFragment extends XBaseFragment {

    @ViewInject(R.id.phone)
    private EditText phone;
    @ViewInject(R.id.code)
    private EditText code;
    @ViewInject(R.id.password)
    private EditText password;
    @ViewInject(R.id.affirm_password)
    private EditText affirmPassword;
    @ViewInject(R.id.get_code)
    private TextView getCode;

    private LoginAndRegistPresenter presenter;

    public RegistFragment(LoginAndRegistPresenter presenter) {
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

    @Event(value = {R.id.get_code, R.id.regist})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_code:
                getCode();
                break;
            case R.id.regist:
                regist();
                break;
        }
    }

    public void getCode() {
        presenter.requestCode(phone.getText().toString(),getCode.getText().toString());
    }


    private void regist() {
        presenter.requestRegist(phone.getText().toString(), password.getText().toString(), affirmPassword.getText().toString(), code.getText().toString());
    }


    public void setCodeBtnText(String text) {
        getCode.setText(text);
    }
}
