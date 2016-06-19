package com.jshs.mobile.banmen.FunctionPage.StartPager;

import com.jshs.mobile.banmen.BaseContent.BaseLayer;

/**
 * Created by Icezers on 2016/6/14.
 */
public interface LoginAndRegistView extends BaseLayer {

    void showToast(String Message);

    void onCodeCountDown(int s);

    void onCodeCountDownComplete();

    boolean isProgressDiaologShow();

    void showProgressDiaolog();

    void hideProgressDiaolog();

    void onLoginSuccess();
}
