package com.virtualightning.library.universalview;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class BaseDataBundle {
    protected List<Serializable> headerList;
    protected List<Serializable> contentList;

    public BaseDataBundle() {
        headerList = new ArrayList<>();
        contentList = new ArrayList<>();
    }

    public final boolean isEmpty() {
        return headerList.size() == 0 && contentList.size() == 0;
    }

    public void setValue(String key, Object value) {

    }

    public Object getValue(String key) {
        return null;
    }

    public Map<String, Object> onSaveInstanceState() {
        return null;
    }

    public void onRestoreInstance(Map<String, Object> objectMap) {

    }

    public void onRefreshAllBefore(boolean isInterrupt, Mediator mediator) {
    }

    public void onRefreshContentBefore(boolean isInterrupt, Mediator mediator) {
    }

    public void onPreloadPageBefore(Mediator mediator) {

    }

    public void onRefreshCompleted(BaseResultBundle resultBundle, Mediator mediator) {

    }

    public void onUpdateData(boolean isHeaderList, int position, Object data, Object arg) {

    }
}
