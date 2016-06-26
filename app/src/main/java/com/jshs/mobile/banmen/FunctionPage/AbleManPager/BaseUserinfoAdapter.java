package com.jshs.mobile.banmen.FunctionPage.AbleManPager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.R;

import java.util.ArrayList;
import java.util.List;

public class BaseUserinfoAdapter<T> extends BaseAdapter {

    private List<T> datas = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;
    private String actionContent;

    public BaseUserinfoAdapter(Context context, String actionContent) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.actionContent = actionContent;
    }

    public BaseUserinfoAdapter(Context context, String actionContent, List<T> datas) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
        this.actionContent = actionContent;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.base_userinfo_listitem, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.position = position;
        initHolderViews(getItem(position), holder, position);
        convertView.setOnClickListener(onItemClickListener);
        return convertView;
    }

    private void initHolderViews(T data, ViewHolder holder, int position) {
    }

    private void onItemClick(View convertView, T data, ViewHolder holder, int position) {
        context.startActivity(new Intent(context, NetUserFeiActivity.class));
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ViewHolder holder = (ViewHolder) v.getTag();
            if (holder == null || holder.position >= datas.size())
                return;
            onItemClick(v, datas.get(holder.position), holder, holder.position);
        }
    };

    protected class ViewHolder {
        private SimpleDraweeView icon;
        private TextView name;
        private TextView content;
        private TextView action;
        private int position;

        public ViewHolder(View view) {
            icon = (SimpleDraweeView) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.name);
            content = (TextView) view.findViewById(R.id.content);
            action = (TextView) view.findViewById(R.id.action);

            if (actionContent == null) {
                action.setVisibility(View.GONE);
            } else {
                action.setVisibility(View.VISIBLE);
                action.setText(actionContent);
            }

            icon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));
        }
    }
}
