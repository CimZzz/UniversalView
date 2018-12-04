package com.virtualightning.library.universalview;

import com.virtualightning.library.universalview.bases.BaseViewPicker;
import com.virtualightning.library.universalview.interfaces.IDataUpdateVisitor;
import com.virtualightning.library.universalview.interfaces.ILayoutManagerGenerator;
import com.virtualightning.library.universalview.interfaces.IPreloadStrategyGenerator;
import com.virtualightning.library.universalview.interfaces.ISplitDecorationGenerator;
import com.virtualightning.library.universalview.interfaces.IUniversalRequestCallback;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
class InnerParams {
    /*Callback*/



    /*Values*/
    boolean isRefreshAllInterrupt;
    boolean isRefreshContentInterrupt;
    boolean isAllowPullRefresh;

    int layoutType;

    int pageNum;
}
