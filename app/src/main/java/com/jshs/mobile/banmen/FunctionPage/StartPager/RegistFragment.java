package com.jshs.mobile.banmen.FunctionPage.StartPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseFragment;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Start.GetSmsRequest;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return getContent();
    }

    @Event(value = {R.id.get_code, R.id.regist})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.code:
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
                
            }
        }, new CodeErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }

    private void regist() {
    }
}
