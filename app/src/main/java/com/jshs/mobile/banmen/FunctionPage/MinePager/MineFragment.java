package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.Events.ChangeUserInfoEvent;
import com.jshs.mobile.banmen.FunctionPage.AbleManPager.BusinessCardActivity;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.R;
import com.jshs.mobile.banmen.Tools.TextTools;
import com.jshs.mobile.banmen.Tools.TitleHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;

/**
 * Created by icezers on 16/6/8.
 */

@ContentView(R.layout.mine_fragment)
public class MineFragment extends BaseHomePager implements View.OnClickListener, TitleHolder.TitleBtnClick {

    private SimpleDraweeView headIcon;
    private TextView nickName;
    private TextView phone;
    private TextView supplierInfoText;

    private void assignViews() {
        headIcon = mFindViewById(R.id.head_icon);
        supplierInfoText = mFindViewById(R.id.supplier_info_text);
        nickName = mFindViewById(R.id.nickname);
        phone = mFindViewById(R.id.phone);
    }

    private int[] clickIds = {R.id.upgrade_supplier, R.id.scan, R.id.user_info, R.id.my_card, R.id.supplier_info, R.id.my_pictures, R.id.my_collect, R.id.my_fans, R.id.my_downloads};
    private ArrayList<View> clickViews = new ArrayList<>(clickIds.length);
    private ArrayList<Class> targetAcitivitys = new ArrayList<>(clickIds.length);

    @Override
    public void initViews() {
        assignViews();

        titleHolder = new TitleHolder(getActivity(), getContent(), R.string.mine);
        titleHolder.initBtns(R.drawable.iconfont_setting, R.drawable.iconfont_remind, this);
    }

    @Override
    public void initData() {
        for (int i = 0; i < clickIds.length; i++) {
            View view = mFindViewById(clickIds[i]);
            view.setOnClickListener(this);
            view.setTag(i);
        }
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(MineScanActivity.class);
        targetAcitivitys.add(MineInfoActivity.class);
        targetAcitivitys.add(BusinessCardActivity.class);
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(MIneGalleryActivity.class);
        targetAcitivitys.add(MineCollectActivity.class);
        targetAcitivitys.add(MineFansActivity.class);
        targetAcitivitys.add(MineDownloadActivity.class);
    }

    @Override
    public void initAction() {
        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));

        refreshUserUI();
    }

    private void refreshUserUI() {
        if (UserUtils.getInstance().isLogin()) {
            User user = UserUtils.getInstance().getUser();
            if (!TextUtils.isEmpty(user.getThumbnail())) {
                headIcon.setImageURI(Uri.parse(TextTools.getNotNull(user.getThumbnail())));
            } else {
                // TODO: 2016/6/16
                // 清空头像的显示效果
            }
            nickName.setText(TextTools.getNotNull(user.getNickname()));
            phone.setText(TextTools.getNotNull(user.getMobile()));
        } else {
            // TODO: 2016/6/16
            // 清空头像的显示效果

            nickName.setText("");
            phone.setText("");
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), targetAcitivitys.get((Integer) view.getTag()));
        switch (view.getId()) {
            case R.id.my_card:
                intent.putExtra("isMe", true);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onPagerSelect() {

    }

    @Override
    public void onLeftBtnClick(View view) {
        startActivity(new Intent(getActivity(), SetActivity.class));
    }

    @Override
    public void onRightBtnClick(View view) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onChangeUserInfo(ChangeUserInfoEvent event) {
        refreshUserUI();
    }
}
