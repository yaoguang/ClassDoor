package com.jshs.mobile.banmen.UIComponents.AutoView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.jshs.mobile.banmen.R;
/**
 * This class inherits from ImageView to auto adjust height by with.
 */
public class AutoAdjustHeightImageView extends ImageView {
    private float height_ratio = -1;
    private int width;
    private boolean changed = false;

    /**
     * Constructor
     *
     * @param tContext
     */
    public AutoAdjustHeightImageView(Context tContext) {
        super(tContext);
    }

    /**
     * Constructor
     *
     * @param tContext
     * @param tAttributeSet
     */
    public AutoAdjustHeightImageView(Context tContext,
                                     AttributeSet tAttributeSet) {
        super(tContext, tAttributeSet);
        TypedArray a = tContext.getTheme().obtainStyledAttributes(tAttributeSet,
                R.styleable.AutoAdjustHeightImageView, 0, 0);
        try {
            height_ratio = a.getFloat(
                    R.styleable.AutoAdjustHeightImageView_iv_height_ratio,
                    -1);
        } finally {
            a.recycle();
        }
    }

    /**
     * Constructor
     *
     * @param tContext
     * @param tAttributeSet
     * @param defStyle
     */
    public AutoAdjustHeightImageView(Context tContext,
                                     AttributeSet tAttributeSet, int defStyle) {
        super(tContext, tAttributeSet, defStyle);
    }

    /**
     * Override onMeasure to adjust height.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!changed) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
            changed = true;
            if (height_ratio != -1) {
                getLayoutParams().height = (int) (width * height_ratio);
                requestLayout();
            }
        }

        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = null;
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            }
            if (drawable instanceof StateListDrawable) {
                Drawable drawable2 = ((StateListDrawable) drawable)
                        .getCurrent();
                bitmap = ((BitmapDrawable) drawable2).getBitmap();
            }

            if (drawable instanceof NinePatchDrawable) {
                Drawable drawable3 = ((NinePatchDrawable) drawable);
                bitmap = ((BitmapDrawable) drawable3).getBitmap();
            }

            if (bitmap != null) {
                int imageWidth = bitmap.getWidth();
                int imageHeight = bitmap.getHeight();
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = measuredWidth * imageHeight / imageWidth;
                setMeasuredDimension(measuredWidth, measuredHeight);
            }
        }
    }
}
