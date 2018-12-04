package com.virtualightning.library.universalview;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class BaseResultBundle {
    WeakReference<Mediator> mediatorWeakReference;
    int refreshType;

    int refreshAllCode;
    int refreshContentCode;
    int refreshPageCode;
    boolean isInterrupt;
    int pageNum;

    boolean isSend;
    boolean isSuccess;

    private List<Serializable> headerList;

    private List<Serializable> contentList;

    private boolean isOver;

    public int getPageNum() {
        return pageNum;
    }

    public int getRefreshType() {
        return refreshType;
    }

    public boolean isInterrupt() {
        return isInterrupt;
    }

    public void setHeaderList(List<Serializable> headerList) {
        this.headerList = headerList;
    }

    public List<Serializable> getHeaderList() {
        return headerList;
    }

    public void setContentList(List<Serializable> contentList) {
        this.contentList = contentList;
    }

    public List<Serializable> getContentList() {
        return contentList;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public final void sendTarget(boolean isSuccess) {
        if(this.isSend)
            return;
        this.isSend = true;

        Mediator mediator =  mediatorWeakReference.get();
        if(mediator == null)
            return;

        this.isSuccess = isSuccess;
        mediator.sendResultBundle(this);
    }
}
