package com.jshs.mobile.banmen.FunctionPage.GalleryPager;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.UIComponents.AutoView.AutoAdjustHeightImageView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by icezers on 16/7/4.
 */

public class SimpleRecyclerCardAdapter extends RecyclerView.Adapter<SimpleCardViewHolder> {

	private Context mCtx;
	private LayoutInflater mInflater;
	private final List<String> mDataSource = new ArrayList<String>();
	private OnItemActionListener mOnItemActionListener;

	public SimpleRecyclerCardAdapter(Context mCtx) {
		super();
		this.mCtx = mCtx;
		mInflater = LayoutInflater.from(mCtx);
	}

	public void setDatas(List<String> dataList) {
		this.mDataSource.addAll(dataList);
		this.notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		return mDataSource.size();
	}

	@Override
	public void onBindViewHolder(final SimpleCardViewHolder viewHolder, int i) {
		x.image().bind(viewHolder.itemIv, mDataSource.get(i));
		if (mOnItemActionListener != null) {
			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
					mOnItemActionListener.onItemClickListener(v, viewHolder.getPosition());
				}
			});
			viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					//注意这里必须使用viewHolder.getPosition()而不能用i，因为为了保证动画，没有使用NotifyDatasetChanged更新位置数据
					return mOnItemActionListener.onItemLongClickListener(v, viewHolder.getPosition());
				}
			});
		}
	}

	@Override
	public SimpleCardViewHolder onCreateViewHolder(ViewGroup viewgroup, int i) {

		View v = mInflater.inflate(R.layout.item_gallery_layout, viewgroup, false);
		SimpleCardViewHolder simpleViewHolder = new SimpleCardViewHolder(v);
		simpleViewHolder.setIsRecyclable(true);

		return simpleViewHolder;
	}

	/**********
	 * 定义点击事件
	 **********/
	public interface OnItemActionListener {
		public void onItemClickListener(View v, int pos);

		public boolean onItemLongClickListener(View v, int pos);
	}

	public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
		this.mOnItemActionListener = onItemActionListener;
	}
}

class SimpleCardViewHolder extends ViewHolder {
	public AutoAdjustHeightImageView itemIv;

	public SimpleCardViewHolder(View layout) {
		super(layout);
		itemIv = (AutoAdjustHeightImageView) layout.findViewById(R.id.item_img);
	}
}