package com.virtualightning.library.universalview;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public final class UniversalConstant {
    /*常量*/
    public static final int REFRESH_ALL = 0;//内容来源-全部刷新
    public static final int REFRESH_CONTENT = 1;//内容来源-刷新内容
    public static final int REFRESH_PAGE = 2;//内容来源-加载页数据

    /*View 显示状态*/
    public static final int VIEW_STATE_LOADING = 1;//显示状态-加载中
    public static final int VIEW_STATE_CONTENT = 2;//显示状态-内容
    public static final int VIEW_STATE_ERROR = 3;//显示状态-加载失败

    public static final int LAYOUT_ALL = 0;
    public static final int LAYOUT_HEADER = 1;
    public static final int LAYOUT_CONTENT = 2;

    private UniversalConstant() { }
}
