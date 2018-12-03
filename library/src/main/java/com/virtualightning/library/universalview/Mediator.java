package com.virtualightning.library.universalview;

import android.content.Context;

import com.virtualightning.library.universalview.bases.BaseViewPicker;
import com.virtualightning.library.universalview.interfaces.IUniversalRequestCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class Mediator {

    final Context context;
    private final UniversalView universalView;
    private final UniversalRequestResolver requestResolver;
    private final InnerState innerState;
    InnerParams innerParams;
    InnerDataBundle innerDataBundle;

    public Mediator(Context context, UniversalView universalView) {
        this.context = context;
        this.universalView = universalView;
        this.requestResolver = new UniversalRequestResolver(this);
        this.innerState = new InnerState();
        this.innerParams = new InnerParams();
        this.innerDataBundle = new InnerDataBundle();
    }


    public void setViewState(int viewState) {
        this.innerState.viewState = viewState;
        this.universalView.checkState(viewState);
    }

    public boolean isAllowPullRefresh() {
        return innerParams.isAllowPullRefresh;
    }

    /*内部使用参数*/
    BaseViewPicker viewPicker;

    /*内部使用数据*/
}
