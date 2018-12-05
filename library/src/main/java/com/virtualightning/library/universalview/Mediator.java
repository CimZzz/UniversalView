package com.virtualightning.library.universalview;

import android.content.Context;

import com.virtualightning.library.universalview.interfaces.IItemViewCallback;
import com.virtualightning.library.universalview.interfaces.ILayoutManagerGenerator;
import com.virtualightning.library.universalview.interfaces.IPreloadStrategyGenerator;
import com.virtualightning.library.universalview.interfaces.ISplitDecorationGenerator;
import com.virtualightning.library.universalview.interfaces.IUniversalRequestCallback;
import com.virtualightning.library.universalview.interfaces.IViewOperatorCallback;


/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class Mediator {

    final Context context;
    private final UniversalView universalView;
    private final UniversalRequestResolver requestResolver;
    private final InnerState innerState;
    InnerParams innerParams;
    IViewMode viewMode;

    public Mediator(Context context, UniversalView universalView) {
        this.context = context;
        this.universalView = universalView;
        this.requestResolver = new UniversalRequestResolver(this);
        this.innerState = new InnerState();
        this.innerParams = new InnerParams();
    }

    public void setViewState(int viewState) {
        boolean isChanged = viewState != this.innerState.viewState;
        this.innerState.viewState = viewState;
        if(isChanged) {
            if(innerParams.isAllowUpdateImmediately)
                this.universalView.checkState(viewState);
            else innerParams.isNeedUpdateShow = true;
        }
    }

    public void refreshAll(boolean isInterrupt) {
        dataBundle.onRefreshAllBefore(isInterrupt, this);
        requestResolver.refreshAll(isInterrupt);
    }

    public void refreshContent(boolean isInterrupt) {
        dataBundle.onRefreshContentBefore(isInterrupt, this);
        requestResolver.refreshContent(isInterrupt);
    }

    void setAllowUpdateImmediately(boolean allowUpdateImmediately) {
        if(innerParams.isAllowUpdateImmediately == allowUpdateImmediately)
            return;
        innerParams.isAllowUpdateImmediately = allowUpdateImmediately;

        if(allowUpdateImmediately) {
            if(innerParams.isNeedUpdateShow)
                this.universalView.checkState(innerState.viewState);

            if(innerParams.isNeedUpdateHeader)
                this.viewMode.updateHeader();

            if(innerParams.isNeedUpdateContent)
                viewMode.updateContent(innerParams.isOver);

            innerParams.initAllUpdateOption();
        }
    }

    public void updateHeader() {
        if(viewMode == null)
            return;
        if(innerParams.isAllowUpdateImmediately)
            viewMode.updateHeader();
        else innerParams.isNeedUpdateHeader = true;
    }

    public void updateContent(boolean isOver) {
        if(viewMode == null)
            return;

        if(innerParams.isAllowUpdateImmediately)
            viewMode.updateContent(isOver);
        else {
            innerParams.isNeedUpdateContent = true;
            innerParams.isOver = isOver;
        }
    }

    public void appendContent(int appendCount, boolean isOver) {
        if(viewMode == null)
            return;

        if(innerParams.isAllowUpdateImmediately)
            viewMode.append(appendCount, isOver);
        else {
            innerParams.isNeedUpdateContent = true;
            innerParams.isOver = isOver;
        }
    }
    public void setValue(String key, Object value) {
        dataBundle.setValue(key, value);
    }

    public Object getValue(String key) {
        return dataBundle.getValue(key);
    }

    public <T> T getValue(String key, T defaultValue) {
        Object value = dataBundle.getValue(key);
        if(value == null && defaultValue == null)
            return null;
        else if(value == null)
            return defaultValue;
        else if(defaultValue != null) {
            if (defaultValue.getClass().equals(value.getClass()))
                return (T) value;
            else return null;
        }
        else return (T) value;
    }

    public void updateHeaderData(int position, int viewType, Object arg) {
        if(viewMode == null)
            return;

        viewMode.updateHeaderData(position, viewType, arg, innerParams.isAllowUpdateImmediately);
        if(!innerParams.isAllowUpdateImmediately)
            innerParams.isNeedUpdateHeader = true;
    }

    public void updateContentData(int position, Object arg) {
        if(viewMode == null)
            return;

        viewMode.updateContentData(position, arg, innerParams.isAllowUpdateImmediately);
        if(!innerParams.isAllowUpdateImmediately)
            innerParams.isNeedUpdateContent = true;
    }

    public void attachView(IViewOperatorCallback viewOperatorCallback) {
        if(viewOperatorCallback == null)
            return;

        viewOperatorCallback.onAttachView(universalView);
    }

    public void detachView(IViewOperatorCallback viewOperatorCallback) {
        if(viewOperatorCallback == null)
            return;

        viewOperatorCallback.onDetachView(universalView);
    }

    public Context getContext() {
        return context;
    }

    /*内部使用参数*/
    ISplitDecorationGenerator splitDecorationGenerator;
    IUniversalRequestCallback universalRequestCallback;
    ILayoutManagerGenerator layoutManagerGenerator;
    IPreloadStrategyGenerator preloadStrategyGenerator;
    IItemViewCallback itemViewCallback;
    BaseViewPicker viewPicker;
    BaseDataBundle dataBundle;

    public BaseViewPicker getViewPicker() {
        return viewPicker;
    }

    public BaseDataBundle getDataBundle() {
        return dataBundle;
    }

    /*内部使用数据*/

    /*内部使用方法*/

    void initViewMode() {
        viewMode = new AllMode(this);
        viewMode.createRootView(context, universalView);
    }

    void preloadPage() {
        dataBundle.onPreloadPageBefore(this);
        requestResolver.nextPage();
    }

    void sendResultBundle(BaseResultBundle baseResultBundle) {
        if(baseResultBundle == null)
            return;

        requestResolver.handleResultBundle(baseResultBundle);
    }

    boolean checkCompleted(BaseResultBundle resultBundle) {
        if(!requestResolver.checkResultBundleValid(resultBundle))
            return false;

        requestResolver.completed(resultBundle);
        return true;
    }

    void saveInstanceState(SavedStated savedStated) {
        savedStated.viewState = innerState.viewState;
        if(innerState.viewState == UniversalConstant.VIEW_STATE_CONTENT &&
                dataBundle != null &&
                !dataBundle.isEmpty()) {
            savedStated.headerList = dataBundle.headerList;
            savedStated.contentList = dataBundle.contentList;
            savedStated.objectMap = dataBundle.onSaveInstanceState();
        }
        else if(innerState.viewState == UniversalConstant.VIEW_STATE_CONTENT)
            savedStated.viewState = UniversalConstant.VIEW_STATE_LOADING;
    }

    void restoreInstanceState(SavedStated savedStated) {
        int viewState = savedStated.viewState;
        switch (viewState) {
            case UniversalConstant.VIEW_STATE_LOADING:
                setViewState(viewState);
                refreshAll(true);
                break;

            case UniversalConstant.VIEW_STATE_ERROR:
                setViewState(viewState);
                break;

            case UniversalConstant.VIEW_STATE_CONTENT:
                dataBundle.headerList = savedStated.headerList;
                dataBundle.contentList = savedStated.contentList;
                dataBundle.onRestoreInstance(savedStated.objectMap);
                setViewState(viewState);
                break;
        }
    }
}
