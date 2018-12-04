package com.virtualightning.library.universalview;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;

import com.virtualightning.library.universalview.interfaces.IErrorViewGenerator;
import com.virtualightning.library.universalview.interfaces.ILoadingViewGenerator;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalView extends FrameLayout {
    private boolean isInit;
    private IErrorViewGenerator errorViewGenerator;
    private ILoadingViewGenerator loadingViewGenerator;

    private View loadingView;
    private View errorView;

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

    public void initUniversalView(UniversalBuilder builder) {
        if(isInit || !builder.checkInit())
            return;
        this.isInit = true;
        this.errorViewGenerator = builder.errorViewGenerator;
        this.loadingViewGenerator = builder.loadingViewGenerator;

        this.mediator.splitDecorationGenerator = builder.splitDecorationGenerator;
        this.mediator.universalRequestCallback = builder.universalRequestCallback;
        this.mediator.layoutManagerGenerator = builder.layoutManagerGenerator;
        this.mediator.preloadStrategyGenerator = builder.preloadStrategyGenerator;
        this.mediator.itemViewCallback = builder.itemViewCallback;
        if(this.mediator.itemViewCallback instanceof BaseItemViewCallback)
            ((BaseItemViewCallback) this.mediator.itemViewCallback).mediator = mediator;

        this.mediator.viewPicker = builder.viewPicker;
        this.mediator.viewPicker.setMediator(this.mediator);
        this.mediator.dataBundle = builder.dataBundle;
    }

    public void refreshAll(boolean isInterrupt) {
        if(isInit)
            this.mediator.refreshAll(isInterrupt);
    }

    public void refreshContent(boolean isInterrupt) {
        if(isInit)
            this.mediator.refreshContent(isInterrupt);
    }

    public void setAllowUpdateImmediately(boolean allowUpdateImmediately) {
        if(isInit)
            this.mediator.setAllowUpdateImmediately(allowUpdateImmediately);
    }


    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        SavedStated savedStated = new SavedStated();
        savedStated.originalStated = super.onSaveInstanceState();
        mediator.saveInstanceState(savedStated);
        return savedStated;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    /*控件显示方法*/
    void checkState(int viewState) {
        switch (viewState) {
            case UniversalConstant.VIEW_STATE_LOADING:
                removeErrorView();
                removeContentView();
                showLoadingView();
                break;
            case UniversalConstant.VIEW_STATE_ERROR:
                removeLoadingView();
                removeContentView();
                showErrorView();
                break;
            case UniversalConstant.VIEW_STATE_CONTENT:
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
        if(mediator.viewMode == null)
            mediator.initViewMode();

        View rootView = mediator.viewMode.getRootView();
        if(rootView == null)
            return;

        if(rootView.getParent() != null)
            return;

        addView(rootView);
    }

    private void removeContentView() {
        if(mediator.viewMode == null)
            return;

        View rootView = mediator.viewMode.getRootView();
        if(rootView == null)
            return;

        if(rootView.getParent() == null)
            return;

        removeView(rootView);
    }
}
