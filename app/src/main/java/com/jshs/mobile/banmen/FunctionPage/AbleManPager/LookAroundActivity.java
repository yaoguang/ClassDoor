package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jshs.mobile.banmen.BaseContent.XBaseActivity;
import com.jshs.mobile.banmen.Http.AbleMan.AddFriendRequest;
import com.jshs.mobile.banmen.Http.AbleMan.LookAroundRequest;
import com.jshs.mobile.banmen.Http.AsyncHttp;
import com.jshs.mobile.banmen.Http.CodeErrorListener;
import com.jshs.mobile.banmen.Http.Result;
import com.jshs.mobile.banmen.Models.AbleMan.AbleMan;
import com.jshs.mobile.banmen.Models.AbleMan.AroundAbleMan;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.ScreenUtils;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

/**
 * Created by SZH on 2016/6/25.
 */
@ContentView(R.layout.look_around_activity)
public class LookAroundActivity extends XBaseActivity {
    @ViewInject(R.id.listview)
    private ListView listView;
    private ArrayList<AroundAbleMan> datas = new ArrayList<>();
    private BaseUserinfoAdapter<AroundAbleMan> adapter;

    private String distance = "500米以内";
    private View distanceContent;
    private PopupWindow distanceWindow;
    private TextView item1;
    private TextView item2;
    private TextView item3;

    public void initViews() {
        titleHolder = new TitleHolder(this, R.string.look_around);
        titleHolder.initBtns(R.drawable.iconfont_addfill, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });

        distanceContent = getLayoutInflater().inflate(R.layout.distance_view, null);
        distanceWindow = new PopupWindow(distanceContent, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        item1 = (TextView) distanceContent.findViewById(R.id.item_1);
        item2 = (TextView) distanceContent.findViewById(R.id.item_2);
        item3 = (TextView) distanceContent.findViewById(R.id.item_3);
    }

    public void initData() {
    }

    protected void initAction() {
        adapter = new BaseUserinfoAdapter(this, getString(R.string.add)) {
            @Override
            protected void initHolderViews(AbleMan data, ViewHolder holder, int position) {
                super.initHolderViews(data, holder, position);
                if (data.relationType == 2)
                    holder.action.setVisibility(View.GONE);
                else
                    holder.action.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onItemClick(View convertView, AbleMan data, ViewHolder holder, int position) {
                switch (convertView.getId()) {
                    case R.id.action:
                        addFriend(datas.get(position).uid);
                        break;
                    default: {
                        Intent intent = new Intent(LookAroundActivity.this, NetUserFeiActivity.class);
                        intent.putExtra("ableMan", datas.get(position));
                        startActivity(intent);
                    }
                    break;
                }
            }
        };
        listView.setAdapter(adapter);

        item1.setOnClickListener(onDistanceItem);
        item2.setOnClickListener(onDistanceItem);
        item3.setOnClickListener(onDistanceItem);

        getLookAround();
    }


    private void showPopWindow() {
        distanceWindow.showAsDropDown(titleHolder.rightBtn, ScreenUtils.getInstance().dpToPx(15), 2);
    }

    private View.OnClickListener onDistanceItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            distance = ((TextView) v).getText().toString();
            distanceWindow.dismiss();
            getLookAround();
        }
    };

    public void getLookAround() {
        AsyncHttp.getInstance().addRequest(new LookAroundRequest("", "", distance, new Response.Listener<LookAroundRequest.LookAroundResult>() {
            @Override
            public void onResponse(LookAroundRequest.LookAroundResult response) {
                datas = response.data;
                adapter.setDatas(datas);
            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }

    public void addFriend(int pid) {
        AsyncHttp.getInstance().addRequest(new AddFriendRequest(pid, new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {

            }
        }, new CodeErrorListener(this) {
            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
            }
        }));
    }
}
