package com.jshs.mobile.banmen.FunctionPage.ServicePager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;

/**
 * Created by icezers on 16/6/8.
 */
public class ServiceFragment extends BaseHomePager {
    View _rootView;
    ListView listView;

    String contents[] = {"混凝土制品", "钢筋混凝土", "商品混凝土"};

    @Override
    public void onPagerSelect() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _rootView = inflater.inflate(R.layout.service_fragment, container, false);
        listView = (ListView) _rootView.findViewById(R.id.service_listview);
        listView.setAdapter(new MyAdapter());
        return _rootView;
    }


    class MyAdapter extends BaseAdapter {

        class ViewHolder {
            TextView name;
        }

        @Override
        public int getCount() {
            return contents.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                view = getLayoutInflater(null).inflate(R.layout.item_service_home_listview, viewGroup, false);
                holder.name = (TextView) view.findViewById(R.id.item_name);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.name.setText(contents[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return view;
        }
    }

}
