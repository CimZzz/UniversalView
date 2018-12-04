package com.virtualightning.library.universalview;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
class InnerParams {

    /*Values*/
    int pageNum;


    boolean isAllowUpdateImmediately = true;

    boolean isNeedUpdateShow;
    boolean isNeedUpdateHeader;
    boolean isNeedUpdateContent;
    boolean isOver;

    void initAllUpdateOption() {
        isNeedUpdateShow = false;
        isNeedUpdateHeader = false;
        isNeedUpdateContent = false;
        isOver = false;
    }
}
