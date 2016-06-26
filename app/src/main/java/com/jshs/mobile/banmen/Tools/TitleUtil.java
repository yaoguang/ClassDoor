package com.jshs.mobile.banmen.Tools;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.jshs.mobile.banmen.BaseContent.BaseApplication;
import com.jshs.mobile.banmen.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/31.
 * <p/>
 * 控制是否为沉浸式状态栏
 * 过滤指定机型不执行操作
 */
public class TitleUtil {
    private static ArrayList<Integer> wrongPhoneINT;
    private static ArrayList<String> wrongPhoneMODEL;

    static {
        wrongPhoneINT = new ArrayList<>();
        wrongPhoneMODEL = new ArrayList<>();

        //oppo R5 (R8109),Android 4.4
        wrongPhoneINT.add(Build.VERSION_CODES.KITKAT);
        wrongPhoneMODEL.add("R8109");
    }

    /**
     * 执行沉浸式状态栏
     *
     * @param activity 目标
     * @param hasTitle 是否有模板Title
     */
    public static boolean processingStatus(Activity activity, boolean hasTitle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            for (int i = 0; i < wrongPhoneINT.size(); i++) {
                if (Build.VERSION.SDK_INT == wrongPhoneINT.get(i) && Build.MODEL.equals(wrongPhoneMODEL.get(i)))
                    return false;
            }

            // 隐藏状态栏
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 隐藏虚拟按键背景
            // activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            // WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (hasTitle)
                changeStatusViewHeight(activity.findViewById(R.id.status_bar));
            return true;
        }
        return false;
    }

    /**
     * 执行沉浸式状态栏
     *
     * @param activity 目标
     * @param hasTitle 是否有模板Title
     */
    public static boolean processingStatus(Activity activity, View content, boolean hasTitle) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            for (int i = 0; i < wrongPhoneINT.size(); i++) {
                if (Build.VERSION.SDK_INT == wrongPhoneINT.get(i) && Build.MODEL.equals(wrongPhoneMODEL.get(i)))
                    return false;
            }

            // 隐藏状态栏
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 隐藏虚拟按键背景
            // activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            // WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (hasTitle)
                changeStatusViewHeight(content.findViewById(R.id.status_bar));
            return true;
        }
        return false;
    }

    public static void changeStatusViewHeight(View status) {
        try {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.getStatusHeight(BaseApplication.getInstance()));
            status.setLayoutParams(params);
            status.requestLayout();
        } catch (Exception e) {
        }
    }
}
