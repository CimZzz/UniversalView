package com.virtualightning.library.universalview;

import android.os.Looper;

import com.virtualightning.library.universalview.bases.ResultBundle;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class RequestResolver {
    /*常量*/
    public static final int REFRESH_ALL = 0;//内容来源-全部刷新
    public static final int REFRESH_CONTENT = 1;//内容来源-刷新内容
    public static final int REFRESH_PAGE = 2;//内容来源-加载页数据

    private final SyncCode refreshAllCode;
    private final SyncCode refreshContentCode;
    private final SyncCode refreshPageCode;

    private final Mediator mediator;

    public RequestResolver(Mediator mediator) {
        this.mediator = mediator;

        this.refreshAllCode = new SyncCode();
        this.refreshContentCode = new SyncCode();
        this.refreshPageCode = new SyncCode();
    }


    void refreshAll(boolean isInterrupt) {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.resultBundleGenerator.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_ALL);
        if(isInterrupt)
    }

    void refreshContent(boolean isInterrupt) {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.resultBundleGenerator.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_CONTENT);
    }

    void nextPage() {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.resultBundleGenerator.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_PAGE);
        resultBundle.setRefreshPageCode();
    }

    void reloadPage() {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.resultBundleGenerator.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_PAGE);
    }




    private boolean checkMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
