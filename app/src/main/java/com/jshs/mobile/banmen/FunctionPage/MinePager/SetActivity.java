package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.os.Bundle;
import android.view.View;

import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.set_activity)
public class SetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.user_info, R.id.password, R.id.phone, R.id.clear_cache, R.id.about, R.id.notescontact, R.id.login_out})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_info:
                break;
            case R.id.password:
                break;
            case R.id.phone:
                break;
            case R.id.clear_cache:
                break;
            case R.id.about:
                break;
            case R.id.notescontact:
                break;
            case R.id.login_out:
                break;
        }
    }
}
