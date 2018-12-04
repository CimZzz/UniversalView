package com.virtualightning.universalview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.BaseViewPicker;
import com.virtualightning.library.universalview.tools.LazyLoadToolkit;
import com.virtualightning.universalview.viewcontrollers.TextViewController;
import com.virtualightning.universalview.viewholders.TextViewHolder;

import java.io.Serializable;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ExtendViewPicker extends BaseViewPicker {
    @Override
    public LazyLoadToolkit.ViewController onHeaderCreate(ViewGroup parent, int position, int dataType) {
        return new TextViewController(parent);
    }

    @Override
    public int getHeaderViewType(Object data, int position) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onContentCreate(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(parent);
    }

    @Override
    public void onContentBind(@NonNull RecyclerView.ViewHolder holder, int viewType, Serializable data) {
        ((TextViewHolder) holder).bindData((String) data);
    }

    @Override
    public int getContentViewType(int position, Serializable data) {
        return 0;
    }

    @Override
    public int getContentSpanSize(int viewType) {
        return 1;
    }
}
