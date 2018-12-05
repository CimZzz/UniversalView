package com.virtualightning.library.universalview.interfaces;

import android.view.View;

import com.virtualightning.library.universalview.UniversalView;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public interface IViewOperatorCallback {
    void onAttachView(UniversalView rootView);
    void onDetachView(UniversalView rootView);
}
