package com.virtualightning.library.universalview.bases;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ResultBundle {
    private int syncCode;
    private int refreshType;

    private int refreshAllCode;
    private int refreshContentCode;
    private int refreshPageCode;

    private boolean isInterrupt;

    private int pageNum;

    private List<Serializable> headerList;

    private List<Serializable> contentList;

    private boolean isOver;

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

    public void setInterrupt(boolean interrupt) {
        isInterrupt = interrupt;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setHeaderList(List<Serializable> headerList) {
        this.headerList = headerList;
    }

    public void setContentList(List<Serializable> contentList) {
        this.contentList = contentList;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
