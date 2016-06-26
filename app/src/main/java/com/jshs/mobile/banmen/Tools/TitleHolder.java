package com.jshs.mobile.banmen.Tools;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.UIComponents.LineView;

/**
 * Created by SZH on 2016/6/26.
 */
public class TitleHolder {
    public View statusBar;
    public RelativeLayout titleContent;
    public TextView titleText;
    public ImageView leftBtn;
    public ImageView rightBtn;
    public LineView titleDivide;

    private void assignViews(Activity activity) {
        statusBar = activity.findViewById(R.id.status_bar);
        titleContent = (RelativeLayout) activity.findViewById(R.id.title_content);
        titleText = (TextView) activity.findViewById(R.id.title_text);
        leftBtn = (ImageView) activity.findViewById(R.id.left_btn);
        rightBtn = (ImageView) activity.findViewById(R.id.right_btn);
        titleDivide = (LineView) activity.findViewById(R.id.title_divide);
    }

    private void assignViews(View view) {
        statusBar = view.findViewById(R.id.status_bar);
        titleContent = (RelativeLayout) view.findViewById(R.id.title_content);
        titleText = (TextView) view.findViewById(R.id.title_text);
        leftBtn = (ImageView) view.findViewById(R.id.left_btn);
        rightBtn = (ImageView) view.findViewById(R.id.right_btn);
        titleDivide = (LineView) view.findViewById(R.id.title_divide);
    }

    private Activity mActivity;
    private View mView;
    private Boolean useView = false;

    public TitleHolder(Activity activity, int titleId) {
        assignViews(activity);
        mActivity = activity;
        TitleUtil.processingStatus(activity, true);
        if (titleText != null)
            titleText.setText(titleId);
    }

    public TitleHolder(Activity activity, View view, int titleId) {
        assignViews(view);
        mView = view;
        mActivity = activity;
        useView = true;
        TitleUtil.processingStatus(activity, view, true);
        if (titleText != null)
            titleText.setText(titleId);
    }

    public static TitleHolder initSimpleTitle(Activity activity, int titleId) {
        TitleHolder holder = new TitleHolder(activity, titleId);
        holder.initBtns(0, null);
        return holder;
    }

    public static TitleHolder initSimpleTitle(Activity activity, View view, int titleId) {
        TitleHolder holder = new TitleHolder(activity, titleId);
        holder.initBtns(0, null);
        return holder;
    }

    /**
     * @param leftIconId   -1 gone  0  default >1 资源Id
     * @param leftListener
     * @param rightIcon    <0 gone  >0 资源id
     * @param rightLisener
     */
    public void initBtns(int leftIconId, View.OnClickListener leftListener, int rightIcon, View.OnClickListener rightLisener) {
        if (leftIconId < 0) {
            leftBtn.setVisibility(View.GONE);
        } else if (leftIconId > 0) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setImageResource(leftIconId);
        } else {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });
        }
        if (leftListener != null)
            leftBtn.setOnClickListener(leftListener);

        if (rightIcon > 0) {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setImageResource(rightIcon);
        } else {
            rightBtn.setVisibility(View.GONE);
        }
        if (rightLisener != null)
            rightBtn.setOnClickListener(rightLisener);
    }

    /**
     * @param rightIcon    <0 gone  >0 资源id
     * @param rightLisener
     */
    public void initBtns(int rightIcon, View.OnClickListener rightLisener) {
        leftBtn.setVisibility(View.VISIBLE);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

        if (rightIcon > 0) {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setImageResource(rightIcon);
        } else {
            rightBtn.setVisibility(View.GONE);
        }
        if (rightLisener != null)
            rightBtn.setOnClickListener(rightLisener);
    }

    /**
     * @param leftIconId -1 gone  0  default >1 资源Id
     * @param rightIcon  <0 gone  >0 资源id
     */
    public void initBtns(int leftIconId, int rightIcon, final TitleBtnClick listener) {
        if (leftIconId < 0) {
            leftBtn.setVisibility(View.GONE);
        } else if (leftIconId > 0) {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setImageResource(leftIconId);
        } else {
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onBackPressed();
                }
            });
        }

        if (rightIcon > 0) {
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setImageResource(rightIcon);
        } else {
            rightBtn.setVisibility(View.GONE);
        }

        if (listener != null) {
            if (leftBtn != null && leftIconId > 0) {
                leftBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onLeftBtnClick(v);
                    }
                });
            }
            if (rightBtn != null && rightIcon > 0) {
                rightBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onRightBtnClick(v);
                    }
                });
            }
        }
    }

    public interface TitleBtnClick {
        public void onLeftBtnClick(View view);

        public void onRightBtnClick(View view);
    }
}