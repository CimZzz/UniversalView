package com.virtualightning.library.universalview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.virtualightning.library.universalview.interfaces.IErrorViewGenerator;
import com.virtualightning.library.universalview.interfaces.ILoadingViewGenerator;
import com.virtualightning.library.universalview.views.CustomCoordinatorLayout;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalView extends FrameLayout {
    private IErrorViewGenerator errorViewGenerator;
    private ILoadingViewGenerator loadingViewGenerator;

    private View loadingView;
    private View errorView;
    private CustomCoordinatorLayout rootView;

    private Mediator mediator;

    public UniversalView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /*生命周期方法*/

    private void init(Context context) {
        mediator = new Mediator(context, this);
    }

    private void initUniversalView(UniversalBuilder builder) {
        this.errorViewGenerator = builder.errorViewGenerator;
        this.loadingViewGenerator = builder.loadingViewGenerator;


    }

    /*控件显示方法*/
    void checkState(int viewState) {
        switch (viewState) {
            case InnerState.VIEW_STATE_LOADING:
                removeErrorView();
                removeContentView();
                showLoadingView();
                break;
            case InnerState.VIEW_STATE_ERROR:
                removeLoadingView();
                removeContentView();
                showErrorView();
                break;
            case InnerState.VIEW_STATE_CONTENT:
                removeLoadingView();
                removeErrorView();
                showContentView();
                break;
        }
    }

    private void showLoadingView() {
        if(loadingView == null) {
            //init loading view
            if(loadingViewGenerator == null)
                return;

            loadingView = loadingViewGenerator.generateLoadingView(this);

            if(loadingView == null)
                return;
        }
        if(loadingView.getParent() != null)
            return;

        addView(loadingView);
    }

    private void removeLoadingView() {
        if(loadingView == null)
            return;

        if(loadingView.getParent() == null)
            return;

        removeView(loadingView);
    }

    private void showErrorView() {
        if(errorView == null) {
            //init error view
            if(errorViewGenerator == null)
                return;

            errorView = errorViewGenerator.generateErrorView(this);

            if(errorView == null)
                return;
        }


        if(errorView.getParent() != null)
            return;

        addView(errorView);
    }

    private void removeErrorView() {
        if(errorView == null)
            return;

        if(errorView.getParent() == null)
            return;

        removeView(errorView);
    }

    private void showContentView() {

    }

    private void initContentView() {
        if(rootView == null) {
            rootView = (CustomCoordinatorLayout) LayoutInflater.from(this.mediator.context).inflate(R.layout.view_universal_view_content, this, false);
            rootView.setAllowRefresh(this.mediator.innerParams.isAllowPullRefresh);
        }
    }
}
