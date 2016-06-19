package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.R;

import java.util.ArrayList;
import java.util.List;

public class MineDownloadAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public MineDownloadAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public MineDownloadAdapter(Context context, List<T> datas) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = datas;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.mine_download_listitem, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private SimpleDraweeView icon;
        private TextView name;
        private TextView content;

        public ViewHolder(View view) {
            icon = (SimpleDraweeView) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.name);
            content = (TextView) view.findViewById(R.id.content);
        }
    }
}
