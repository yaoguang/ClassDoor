package com.jshs.mobile.banmen.FunctionPage.MinePager;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jshs.mobile.banmen.Models.Gallery;
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
    private List<Gallery> mDataSource = new ArrayList<>();
    private OnItemActionListener mOnItemActionListener;

    private boolean isLoading = false;

    public SimpleRecyclerCardAdapter(Context mCtx) {
        super();
        this.mCtx = mCtx;
        mInflater = LayoutInflater.from(mCtx);
    }

    public void setDatas(List<Gallery> dataList) {
        this.mDataSource = new ArrayList<>();
        this.mDataSource.addAll(dataList);
        this.notifyDataSetChanged();
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    public int getItemCount() {
        return mDataSource == null ? 0 : mDataSource.size();
    }

    @Override
    public void onBindViewHolder(final SimpleCardViewHolder viewHolder, int i) {
        x.image().bind(viewHolder.itemIv, mDataSource.get(i).getContent());
        viewHolder.title.setText(String.valueOf(i));
        viewHolder.address.setText("上海市浦东新区");
        viewHolder.name.setText("作者：张三");
        viewHolder.time.setText("发布时间：2016-6-6");
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
        if (i == getItemCount() - 1 && !isLoading) {//已经到达列表的底部
            if (listener != null) {
                isLoading = true;
                listener.onSwipeLastestPosition();
            }
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

    public interface onSwipeLastestPositionListener {
        void onSwipeLastestPosition();
    }

    public void setSwipeLastestPositionListener(onSwipeLastestPositionListener listener) {
        this.listener = listener;
    }

    private onSwipeLastestPositionListener listener;
}

class SimpleCardViewHolder extends ViewHolder {
    public AutoAdjustHeightImageView itemIv;
    public TextView title, name, time, address;

    public SimpleCardViewHolder(View layout) {
        super(layout);
        itemIv = (AutoAdjustHeightImageView) layout.findViewById(R.id.item_img);
        title = (TextView) layout.findViewById(R.id.gallery_title);
        name = (TextView) layout.findViewById(R.id.gallery_name);
        time = (TextView) layout.findViewById(R.id.gallery_time);
        address = (TextView) layout.findViewById(R.id.gallery_address);
    }
}