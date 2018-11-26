package com.virtualightning.library.universalview;

import android.content.Context;

import com.virtualightning.library.universalview.interfaces.IResultBundleGenerator;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class Mediator {
    private final Context context;
    private final UniversalView universalView;
    private final RequestResolver presenter;
    private final InnerState innerState;
    private final InnerParams innerParams;

    IResultBundleGenerator<?> resultBundleGenerator;

    public Mediator(Context context, UniversalView universalView) {
        this.context = context;
        this.universalView = universalView;
        this.presenter = new RequestResolver(this);
        this.innerState = new InnerState();
        this.innerParams = new InnerParams();
    }


    public void setViewState(int viewState) {
        this.innerState.viewState = viewState;
        this.universalView.checkState(viewState);
    }



    /*内部使用参数*/

}
