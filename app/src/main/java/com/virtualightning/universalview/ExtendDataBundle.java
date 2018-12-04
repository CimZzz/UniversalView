package com.virtualightning.universalview;

import com.virtualightning.library.universalview.BaseDataBundle;
import com.virtualightning.library.universalview.BaseResultBundle;
import com.virtualightning.library.universalview.Mediator;
import com.virtualightning.library.universalview.UniversalConstant;

import java.io.Serializable;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ExtendDataBundle extends BaseDataBundle {

    @Override
    public void onUpdateData(boolean isHeaderList, int position, Object data, Object arg) {
        if(isHeaderList)
            headerList.set(position, (Serializable) arg);
        else contentList.set(position, (Serializable) arg);
    }

    @Override
    public void onRefreshAllBefore(boolean isInterrupt, final Mediator mediator) {
        LogUtils.verbose(" <<< RefreshAllBefore : " + isInterrupt);
        headerList.clear();
        contentList.clear();
        mediator.updateHeader();
        mediator.updateContent(false);
        mediator.setViewState(UniversalConstant.VIEW_STATE_LOADING);
    }

    @Override
    public void onRefreshContentBefore(boolean isInterrupt, Mediator mediator) {
        LogUtils.verbose(" <<< RefreshContentBefore : " + isInterrupt);
    }

    @Override
    public void onPreloadPageBefore(Mediator mediator) {
        LogUtils.verbose(" <<< PreloadPageBefore");
    }

    @Override
    public void onRefreshCompleted(BaseResultBundle resultBundle, Mediator mediator) {
        LogUtils.verbose(" <<< RefreshCompleted : " + resultBundle.getRefreshType() + " , " + resultBundle.isSuccess());

        switch (resultBundle.getRefreshType()) {
            case UniversalConstant.REFRESH_ALL:
                if(resultBundle.isSuccess()) {
                    headerList.clear();
                    headerList.addAll(resultBundle.getHeaderList());
                    contentList.clear();
                    contentList.addAll(resultBundle.getContentList());

                    mediator.setViewState(UniversalConstant.VIEW_STATE_CONTENT);
                    mediator.updateHeader();
                    mediator.updateContent(false);
                }
                else {

                }
                break;
            case UniversalConstant.REFRESH_CONTENT:
                if(resultBundle.isSuccess()) {

                }
                else {

                }
                break;
            case UniversalConstant.REFRESH_PAGE:
                if(resultBundle.isSuccess()) {
                    int beforeCount = contentList.size();
                    contentList.addAll(resultBundle.getContentList());

                    mediator.appendContent(contentList.size() - beforeCount, false);
                }
                else {

                }
                break;
        }
    }
}
