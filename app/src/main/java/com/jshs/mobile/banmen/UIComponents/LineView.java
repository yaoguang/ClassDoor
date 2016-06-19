package com.jshs.mobile.banmen.UIComponents;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jshs.mobile.banmen.R;

/**
 * Created by icezers on 16/6/8.
 */
public class LineView extends View {

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(getResources().getColor(R.color.cutting_line));
    }
}
