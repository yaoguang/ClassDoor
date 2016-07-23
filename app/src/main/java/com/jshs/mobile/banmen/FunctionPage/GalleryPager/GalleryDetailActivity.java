package com.jshs.mobile.banmen.FunctionPage.GalleryPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jshs.mobile.banmen.BaseContent.BaseActivity;
import com.jshs.mobile.banmen.Models.Gallery;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TitleHolder;
import com.jshs.mobile.banmen.UIComponents.AutoView.AutoAdjustHeightImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by Icezers on 2016/7/23.
 */

@ContentView(R.layout.gallery_detail_activity)
public class GalleryDetailActivity extends BaseActivity {

    public TitleHolder titleHolder;

    @ViewInject(R.id.gallery_detail_title)
    public TextView detail_title;

    @ViewInject(R.id.gallery_detail_content)
    public TextView detail_content;

    @ViewInject(R.id.gallery_detail_tag)
    public TextView detail_tag;

    @ViewInject(R.id.gallery_detail_name)
    public TextView detail_name;

    @ViewInject(R.id.gallery_detail_time)
    public TextView detail_time;

    @ViewInject(R.id.gallery_detail_img)
    public AutoAdjustHeightImageView detail_img;

    public Gallery data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        titleHolder = TitleHolder.initSimpleTitle(this, R.string.gallery_detail);
        titleHolder.initBtns(R.drawable.iconfont_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, R.drawable.iconfont_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        data = (Gallery) getIntent().getSerializableExtra("data");
        if (data == null) return;
        x.image().bind(detail_img, data.getContent());

        detail_title.setText("现代城市办公楼建筑效果图");
        detail_content.setText("现代城市办公楼建筑效果图现代城市办公楼建筑效果图现代城市办公楼建筑效果图现代城市办公楼建筑效果图现代城市办公楼建筑效果图");
        detail_name.setText("作者：张三");
        detail_time.setText("发布时间：2016.04.05");
        detail_tag.setText("园林设计  环境设计");
    }


}
