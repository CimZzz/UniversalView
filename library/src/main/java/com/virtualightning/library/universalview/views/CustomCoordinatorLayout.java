package com.virtualightning.library.universalview.views;

import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class CustomCoordinatorLayout extends CoordinatorLayout {
    private AppBarLayout headerView;
    private RecyclerView contentView;

    private boolean isAllowRefresh;

    public CustomCoordinatorLayout(Context context) {
        super(context);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAllowRefresh(boolean allowRefresh) {
        isAllowRefresh = allowRefresh;
    }

    public void setHeaderView(AppBarLayout headerView) {
        this.headerView = headerView;
    }

    public void setContentView(RecyclerView contentView) {
        this.contentView = contentView;
    }
}
