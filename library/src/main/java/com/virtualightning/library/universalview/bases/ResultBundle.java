package com.virtualightning.library.universalview.bases;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ResultBundle {
    int syncCode;
    int refreshType;

    int refreshAllCode;
    int refreshContentCode;
    int refreshPageCode;

    public void setSyncCode(int syncCode) {
        this.syncCode = syncCode;
    }

    public void setRefreshType(int refreshType) {
        this.refreshType = refreshType;
    }

    public void setRefreshAllCode(int refreshAllCode) {
        this.refreshAllCode = refreshAllCode;
    }

    public void setRefreshContentCode(int refreshContentCode) {
        this.refreshContentCode = refreshContentCode;
    }

    public void setRefreshPageCode(int refreshPageCode) {
        this.refreshPageCode = refreshPageCode;
    }
}
