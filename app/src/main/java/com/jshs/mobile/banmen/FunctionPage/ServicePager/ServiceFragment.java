package com.jshs.mobile.banmen.FunctionPage.ServicePager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;

/**
 * Created by icezers on 16/6/8.
 */
public class ServiceFragment extends BaseHomePager implements TitleHolder.TitleBtnClick, SwipeRefreshLayout.OnRefreshListener {
	View _rootView;
	ListView listView;

	String contents[] = {"混凝土制品", "钢筋混凝土", "商品混凝土"};

	SwipeRefreshLayout refreshLayout;

	@Override
	public void onPagerSelect() {

	}


	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		_rootView = inflater.inflate(R.layout.service_fragment, container, false);
		refreshLayout = (SwipeRefreshLayout) _rootView.findViewById(R.id.refreshlayout);
		refreshLayout.setOnRefreshListener(this);
		listView = (ListView) _rootView.findViewById(R.id.service_listview);
		listView.setAdapter(new MyAdapter());
		return _rootView;
	}

	@Override
	public void initViews() {
		titleHolder = new TitleHolder(getActivity(), getContent(), R.string.service_title);
		titleHolder.initBtns(R.drawable.iconfont_search, R.drawable.iconfont_iconfontyiwen1, this);
	}

	@Override
	public void onLeftBtnClick(View view) {

	}

	@Override
	public void onRightBtnClick(View view) {

	}

	@Override
	public void onRefresh() {
		refreshLayout.setRefreshing(false);
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
