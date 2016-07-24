package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Mine.ChangeUserInfoRequest;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TextTools;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SZH on 2016/6/19.
 */
@ContentView(R.layout.mine_info_activity)
public class MineInfoActivity extends XBaseActivity {
    private SimpleDraweeView headIcon;
    private EditText nickname;
    private EditText name;
    private RadioButton sexMan;
    private RadioButton sexWoman;
    private RadioButton sexPrivary;
    private TextView birthday;
    private EditText personalizedSignature;
    private EditText phone;

    private void assignViews() {
        headIcon = (SimpleDraweeView) findViewById(R.id.head_icon);
        nickname = (EditText) findViewById(R.id.nickname);
        name = (EditText) findViewById(R.id.name);
        sexMan = (RadioButton) findViewById(R.id.sex_man);
        sexWoman = (RadioButton) findViewById(R.id.sex_woman);
        sexPrivary = (RadioButton) findViewById(R.id.sex_privary);
        birthday = (TextView) findViewById(R.id.birthday);
        personalizedSignature = (EditText) findViewById(R.id.personalized_signature);
        phone = (EditText) findViewById(R.id.phone);
    }

    private Date birthdaydate;
    private DatePickerDialog dateDialog;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void initViews() {
        titleHolder = new TitleHolder(this, R.string.mine_info);
        titleHolder.initBtns(R.drawable.icon_compile, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUserInfo();
            }
        });
        assignViews();
    }

    @Override
    protected void initData() {
        try {
            birthdaydate = dateFormat.parse(TextTools.getNotNull(UserUtils.getInstance().getUser().getBirthday()));
        } catch (ParseException e) {
            birthdaydate = new Date();
        }
    }

    @Override
    protected void initAction() {
        refreshUI();
        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));
    }

    private void refreshUI() {
        User user = UserUtils.getInstance().getUser();
        if (user != null) {
            nickname.setText(TextTools.getNotNull(user.getNickname()));
            name.setText(TextTools.getNotNull(user.getUsername()));
            sexMan.setChecked("男".equals(user.getSex()));
            sexMan.setChecked("女".equals(user.getSex()));
            sexPrivary.setChecked("未知".equals(user.getSex()) || TextUtils.isEmpty(user.getSex()));
            birthday.setText(TextTools.getNotNull(user.getBirthday()));
            personalizedSignature.setText(TextTools.getNotNull(user.getSignature()));
            phone.setText(TextTools.getNotNull(user.getMobile()));
        } else {
            finish();
        }
    }

    private void changeUserInfo() {
        AsyncHttp.getInstance().addRequest(
                new ChangeUserInfoRequest(
                        phone.getText().toString(),
                        null,
                        sexMan.isClickable() ? "男" : sexWoman.isClickable() ? "女" : "未知",
                        dateFormat.format(birthdaydate),
                        new Response.Listener<Result>() {
                            @Override
                            public void onResponse(Result response) {
                                refreshUI();
                            }
                        },
                        new CodeErrorListener(this) {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                super.onErrorResponse(error);
                            }
                        }
                ));
    }

    @Event(R.id.birthday)
    private void changeBirthday(View view) {
        if (dateDialog == null)
            dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    birthdaydate = new Date(year - 1900, monthOfYear, dayOfMonth);
                    birthday.setText(dateFormat.format(birthdaydate));
                }
            }, birthdaydate.getYear(), birthdaydate.getMonth(), birthdaydate.getDay());
        dateDialog.show();
    }
}
