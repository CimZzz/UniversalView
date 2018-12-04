package com.virtualightning.universalview;

import android.util.Log;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public final class LogUtils {

    public static void verbose(Object content) {
        Log.v("CimZzz", content == null ? "null" : content.toString());
    }
}
