package com.virtualightning.library.universalview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.Mediator;
import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

import java.io.Serializable;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public abstract class BaseViewPicker {

    private Mediator mediator;
    public BaseViewPicker() {
    }

    void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    /*Header*/
    public LazyLoadToolkit.ViewController onHeaderCreate(ViewGroup parent, int position, int dataType) {
        return null;
    }

    public int getHeaderViewType(Object data, int position) {
        return 0;
    }



    /*Content*/
    public RecyclerView.ViewHolder onContentCreate(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public void onContentBind(@NonNull RecyclerView.ViewHolder holder, int viewType, Serializable data) {

    }

    public int getContentViewType(int position, Serializable data) {
        return 0;
    }

    public int getContentSpanSize(int viewType) {
        return 0;
    }
}
