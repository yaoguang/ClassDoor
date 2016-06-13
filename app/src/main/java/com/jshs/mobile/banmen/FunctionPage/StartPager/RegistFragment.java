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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Start.GetSmsRequest;
import com.jshs.mobile.banmen.Http.Start.RegistRequest;
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


    @Nullable
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
        if (phone.getText().length() != 11) {
            Toast.makeText(getActivity(), "手机号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        requestCode();
    }

    private void requestCode() {
        AsyncHttp.getInstance().addRequest(new GetSmsRequest(phone.getText().toString(), GetSmsRequest.SMSType.REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                codeCountDown();
            }
        }, new CodeErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                Toast.makeText(getActivity(), "获取短信失败", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void regist() {
        if (phone.length() != 11) {
            Toast.makeText(getActivity(), "手机号格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (affirmPassword.getText().toString().equals(password.getText().toString())) {
            Toast.makeText(getActivity(), "确认密码有误", Toast.LENGTH_SHORT).show();
            return;
        }
        if (code.length() != 4) {
            Toast.makeText(getActivity(), "验证码格式错误", Toast.LENGTH_SHORT).show();
            return;
        }
        requestRegist();
    }

    private void requestRegist() {
        AsyncHttp.getInstance().addRequest(new RegistRequest(phone.getText().toString(), password.getText().toString(), code.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    }
                },
                new CodeErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        super.onErrorResponse(error);
                        Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }));
    }

    // 倒计时
    private boolean isCountDown = false;
    private Handler handler;
    private int countDownTime;

    private void codeCountDown() {
        countDownTime = 60;
        isCountDown = true;
        handler.post(countDown);
    }

    Runnable countDown = new Runnable() {
        @Override
        public void run() {
            if (countDownTime > 0) {
                getCode.setText(String.valueOf(countDownTime)
                        + "秒");
                countDownTime--;
                handler.postDelayed(countDown, 1000);
            } else {
                isCountDown = false;
                getCode.setText("获取验证码");
            }
        }
    };
}
