package com.virtualightning.library.universalview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.Mediator;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : YIQIMMM<br>
 * Since : YIQIMMM_2.27<br>
 * Description:<br>
 */
interface IViewMode {
    View createRootView(Context context, ViewGroup parent);
}
