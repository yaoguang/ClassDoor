package com.jshs.mobile.banmen.FunctionPage.MinePager;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jshs.mobile.banmen.BaseContent.BaseHomePager;
import com.jshs.mobile.banmen.ModelUtils.UserUtils;
import com.jshs.mobile.banmen.Models.User;
import com.jshs.mobile.banmen.R;

import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;

/**
 * Created by icezers on 16/6/8.
 */

@ContentView(R.layout.mine_fragment)
public class MineFragment extends BaseHomePager implements View.OnClickListener {

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
    }

    @Override
    public void initData() {
        for (int i = 0; i < clickIds.length; i++) {
            View view = mFindViewById(clickIds[i]);
            view.setOnClickListener(this);
            view.setTag(i);
        }
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(SetActivity.class);
        targetAcitivitys.add(MineInfoActivity.class);
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(UpgradeSupplierActivity.class);
        targetAcitivitys.add(MineCollectActivity.class);
        targetAcitivitys.add(MineFansActivity.class);
        targetAcitivitys.add(MineDownloadActivity.class);
    }

    @Override
    public void initAction() {
        headIcon.setImageURI(Uri.parse("http://i2.hdslb.com/bfs/face/7c3fe391deb34e8b6f72794474ecad69c5c39494.jpg"));

//        refreshUserUI();
    }

    private void refreshUserUI() {
        if (UserUtils.getInstance().isLogin()) {
            User user = UserUtils.getInstance().getUser();
            if (!TextUtils.isEmpty(user.getThumbnail())) {
                headIcon.setImageURI(Uri.parse(user.getThumbnail()));
            } else {
                // TODO: 2016/6/16
                // 清空头像的显示效果
            }
            if (!TextUtils.isEmpty(user.getNickname())) {
                nickName.setText(user.getNickname());
            } else {
                nickName.setText("");
            }
            if (!TextUtils.isEmpty(user.getMobile())) {
                phone.setText(user.getMobile());
            } else {
                phone.setText("");
            }
        } else {
            // TODO: 2016/6/16
            // 清空头像的显示效果

            nickName.setText("");
            phone.setText("");
        }
    }

    public void onClick(View view) {
        startActivity(new Intent(getActivity(), targetAcitivitys.get((Integer) view.getTag())));
    }

    @Override
    public void onPagerSelect() {

    }
}
