package com.virtualightning.library.universalview;

import android.content.Context;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : YIQIMMM<br>
 * Since : YIQIMMM_2.27<br>
 * Description:<br>
 */
interface IViewMode {
    void createRootView(Context context, ViewGroup parent);

    View getRootView();

    void updateHeader();

    void updateContent(boolean isOver);

    void append(int appendCount, boolean isOver);

    void updateHeaderData(int position, int viewType, Object arg, boolean isUpdate);

    void updateContentData(int position, Object arg, boolean isOver);

    void expandHeader(boolean isExpand);

    void contentScrollTo(int position);

    SparseArray<Parcelable> getStatedState();
}
