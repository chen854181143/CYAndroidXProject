package com.chenyang.cyandroidxproject.utils;

import android.content.Context;
import android.util.TypedValue;

public class Utils {
    public static int dip2px(Context mContext,float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, mContext.getResources().getDisplayMetrics());
    }
}
