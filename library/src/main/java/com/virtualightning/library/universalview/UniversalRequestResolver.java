package com.virtualightning.library.universalview;

import android.os.Looper;

import com.virtualightning.library.universalview.bases.ResultBundle;
import static com.virtualightning.library.universalview.UniversalConstant.*;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
@SuppressWarnings("unchecked")
public class UniversalRequestResolver {
    private final SyncCode refreshAllCode;
    private final SyncCode refreshContentCode;
    private final SyncCode refreshPageCode;

    private final Mediator mediator;

    public UniversalRequestResolver(Mediator mediator) {
        this.mediator = mediator;

        this.refreshAllCode = new SyncCode();
        this.refreshContentCode = new SyncCode();
        this.refreshPageCode = new SyncCode();
    }


    void refreshAll(boolean isInterrupt) {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_ALL);
        resultBundle.setRefreshAllCode(refreshAllCode.nextSyncCode());
        resultBundle.setInterrupt(isInterrupt);

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_ALL);
        if(isInterrupt) {
            this.mediator.innerParams.pageNum = 0;
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_CONTENT);
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
            refreshAllCode.recordCode();
        }
        this.mediator.universalRequestCallback.doRequest(REFRESH_ALL, resultBundle);
    }

    void refreshContent(boolean isInterrupt) {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_CONTENT);
        resultBundle.setRefreshAllCode(refreshAllCode.getRecordCode());
        resultBundle.setRefreshContentCode(refreshContentCode.nextSyncCode());
        resultBundle.setInterrupt(isInterrupt);

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_CONTENT);
        if(isInterrupt) {
            this.mediator.innerParams.pageNum = 0;
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
            refreshContentCode.recordCode();
        }
        this.mediator.universalRequestCallback.doRequest(REFRESH_CONTENT, resultBundle);
    }

    void nextPage() {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_CONTENT);
        resultBundle.setRefreshAllCode(refreshAllCode.getRecordCode());
        resultBundle.setRefreshContentCode(refreshContentCode.getRecordCode());
        resultBundle.setRefreshPageCode(refreshPageCode.nextSyncCode());
        resultBundle.setPageNum(this.mediator.innerParams.pageNum + 1);

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
        this.mediator.universalRequestCallback.doRequest(REFRESH_PAGE, resultBundle);
    }

    void reloadPage() {
        if(!checkMain())
            return;

        ResultBundle resultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        resultBundle.setRefreshType(REFRESH_CONTENT);
        resultBundle.setRefreshAllCode(refreshAllCode.getRecordCode());
        resultBundle.setRefreshContentCode(refreshContentCode.getRecordCode());
        resultBundle.setRefreshPageCode(refreshPageCode.nextSyncCode());
        resultBundle.setPageNum(this.mediator.innerParams.pageNum);

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
        this.mediator.universalRequestCallback.doRequest(REFRESH_PAGE, resultBundle);
    }




    private boolean checkMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
