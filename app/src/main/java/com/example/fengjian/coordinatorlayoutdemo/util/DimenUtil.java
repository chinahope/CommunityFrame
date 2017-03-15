package com.example.fengjian.coordinatorlayoutdemo.util;

import android.content.Context;

/**
 * Created by fengjian on 2017/3/14.
 */

public class DimenUtil {
    public static float dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (float) (dp * scale + 0.5);

    }

    public static float pix2dp(Context context, float px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (float) (px / scale + 0.5f);
    }
}
