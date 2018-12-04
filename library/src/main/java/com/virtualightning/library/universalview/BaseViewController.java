package com.virtualightning.library.universalview;

import android.view.ViewGroup;

import com.virtualightning.library.universalview.interfaces.IItemViewCallback;
import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public abstract class BaseViewController<T, E extends IItemViewCallback> extends LazyLoadToolkit.ViewController<T>{
    E callback;

    protected BaseViewController(ViewGroup parent) {
        super(parent);
    }

    protected E getCallback() {
        return this.callback;
    }
}
