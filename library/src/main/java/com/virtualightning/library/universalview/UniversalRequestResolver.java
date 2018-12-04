package com.virtualightning.library.universalview;

import android.os.Looper;

import com.virtualightning.library.universalview.tools.MainUIThread;

import java.lang.ref.WeakReference;

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

        BaseResultBundle baseResultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        baseResultBundle.mediatorWeakReference = new WeakReference<>(mediator);
        baseResultBundle.refreshType = REFRESH_ALL;
        baseResultBundle.refreshAllCode = refreshAllCode.nextSyncCode();
        baseResultBundle.isInterrupt = isInterrupt;
        baseResultBundle.pageNum = 0;

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_ALL);
        if(isInterrupt) {
            this.mediator.innerParams.pageNum = 0;
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_CONTENT);
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
            refreshAllCode.recordCode();
        }
        this.mediator.universalRequestCallback.doRequest(baseResultBundle);
    }

    void refreshContent(boolean isInterrupt) {
        if(!checkMain())
            return;

        BaseResultBundle baseResultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        baseResultBundle.mediatorWeakReference = new WeakReference<>(mediator);
        baseResultBundle.refreshType = REFRESH_CONTENT;
        baseResultBundle.refreshAllCode = refreshAllCode.getRecordCode();
        baseResultBundle.refreshContentCode = refreshContentCode.nextSyncCode();
        baseResultBundle.isInterrupt = isInterrupt;
        baseResultBundle.pageNum = 0;

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_CONTENT);
        if(isInterrupt) {
            this.mediator.innerParams.pageNum = 0;
            this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
            refreshContentCode.recordCode();
        }
        this.mediator.universalRequestCallback.doRequest(baseResultBundle);
    }

    void nextPage() {
        if(!checkMain())
            return;

        BaseResultBundle baseResultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        baseResultBundle.mediatorWeakReference = new WeakReference<>(mediator);
        baseResultBundle.refreshType = REFRESH_PAGE;
        baseResultBundle.refreshAllCode = refreshAllCode.getRecordCode();
        baseResultBundle.refreshContentCode = refreshContentCode.getRecordCode();
        baseResultBundle.refreshPageCode = refreshPageCode.nextSyncCode();
        baseResultBundle.pageNum = this.mediator.innerParams.pageNum + 1;

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
        this.mediator.universalRequestCallback.doRequest(baseResultBundle);
    }

    void reloadPage() {
        if(!checkMain())
            return;

        BaseResultBundle baseResultBundle = this.mediator.universalRequestCallback.generateResultBundle();
        baseResultBundle.mediatorWeakReference = new WeakReference<>(mediator);
        baseResultBundle.refreshType = REFRESH_PAGE;
        baseResultBundle.refreshAllCode = refreshAllCode.getRecordCode();
        baseResultBundle.refreshContentCode = refreshContentCode.getRecordCode();
        baseResultBundle.refreshPageCode = refreshPageCode.nextSyncCode();
        baseResultBundle.pageNum = this.mediator.innerParams.pageNum;

        this.mediator.universalRequestCallback.cancelRequest(REFRESH_PAGE);
        this.mediator.universalRequestCallback.doRequest(baseResultBundle);
    }

    void handleResultBundle(BaseResultBundle baseResultBundle) {
        if(checkResultBundleValid(baseResultBundle)) {
            this.mediator.universalRequestCallback.handleResultBundle(baseResultBundle, this.mediator.dataBundle);
            if(checkMain()) {
                completed(baseResultBundle);
                this.mediator.dataBundle.onRefreshCompleted(baseResultBundle, this.mediator);
            }
            else MainUIThread.postRunnable(new RefreshCompletedRunnable(this.mediator, baseResultBundle));
        }
    }

    boolean checkResultBundleValid(BaseResultBundle baseResultBundle) {
        switch (baseResultBundle.refreshType) {
            case REFRESH_ALL:
                return refreshAllCode.checkSync(baseResultBundle.refreshAllCode);
            case REFRESH_CONTENT:
                return refreshAllCode.checkRecordSync(baseResultBundle.refreshAllCode) &&
                        refreshContentCode.checkSync(baseResultBundle.refreshContentCode);
            case REFRESH_PAGE:
                return refreshAllCode.checkRecordSync(baseResultBundle.refreshAllCode) &&
                        refreshContentCode.checkRecordSync(baseResultBundle.refreshContentCode) &&
                        refreshPageCode.checkSync(baseResultBundle.refreshPageCode);
            default:return false;
        }
    }

    void completed(BaseResultBundle baseResultBundle) {
        if(baseResultBundle.isSuccess()) {
            switch (baseResultBundle.refreshType) {
                case REFRESH_ALL:
                    refreshAllCode.recordCode();
                    mediator.innerParams.pageNum = baseResultBundle.pageNum;
                    break;
                case REFRESH_CONTENT:
                    refreshContentCode.recordCode();
                    mediator.innerParams.pageNum = baseResultBundle.pageNum;
                    break;
                case REFRESH_PAGE:
                    refreshPageCode.recordCode();
                    mediator.innerParams.pageNum = baseResultBundle.pageNum;
                    break;
            }
        }
    }

    private boolean checkMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
