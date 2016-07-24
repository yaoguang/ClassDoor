package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.SearchCondition;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.search_ableman_activity)
public class SearchAbleManActivity extends XBaseActivity {
    private EditText name;
    private EditText number;
    private RadioButton sexMan;
    private RadioButton sexWoman;
    private RadioButton sexPrivary;
    private LinearLayout adressContainer;
    private LinearLayout proContainer;
    private TextView search;
    private TextView addToLink;

    private void assignViews() {
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        sexMan = (RadioButton) findViewById(R.id.sex_man);
        sexWoman = (RadioButton) findViewById(R.id.sex_woman);
        sexPrivary = (RadioButton) findViewById(R.id.sex_privary);
        adressContainer = (LinearLayout) findViewById(R.id.adress_container);
        proContainer = (LinearLayout) findViewById(R.id.pro_container);
        search = (TextView) findViewById(R.id.search);
        addToLink = (TextView) findViewById(R.id.add_to_link);
    }

    @Override
    protected void initViews() {
        titleHolder = TitleHolder.initSimpleTitle(this, R.string.search_anleman);

        assignViews();
    }

    @Override
    protected void initAction() {
        sexMan.setChecked(true);
    }

    @Event(value = {R.id.search, R.id.add_to_link})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
                search();
                break;
            case R.id.add_to_link:
                break;
        }
    }

    private void search() {
        SearchCondition condition = new SearchCondition();
        condition.pid = number.getText().toString();
        condition.job = "job";
        condition.nickname = name.getText().toString();
        condition.sex = sexMan.isChecked() ? "男" : sexWoman.isChecked() ? "女" : "未知";
        condition.adress = "address";

        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("info", condition);
        startActivity(intent);
    }
}
