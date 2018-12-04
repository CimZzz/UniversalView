package com.virtualightning.universalview;

import com.virtualightning.library.universalview.BaseDataBundle;
import com.virtualightning.library.universalview.BaseResultBundle;
import com.virtualightning.library.universalview.UniversalConstant;
import com.virtualightning.library.universalview.interfaces.IUniversalRequestCallback;
import com.virtualightning.library.universalview.tools.MainUIThread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalRequestCallbackImpl implements IUniversalRequestCallback {
    @Override
    public BaseResultBundle generateResultBundle() {
        return new BaseResultBundle();
    }

    @Override
    public void doRequest(final BaseResultBundle resultBundle) {
        LogUtils.verbose(" <<< DoRequest : " + resultBundle.getRefreshType());

        MainUIThread.postRunnableDelay(new Runnable() {
            @Override
            public void run() {
                LogUtils.verbose("send!!");
                resultBundle.sendTarget(true);
            }
        }, 2000);
    }

    @Override
    public void cancelRequest(int refreshType) {
//        LogUtils.verbose(" <<< CancelRequest : " + refreshType);
    }

    @Override
    public void handleResultBundle(BaseResultBundle resultBundle, BaseDataBundle dataBundle) {
        LogUtils.verbose(" <<< handleResultBundle : " + resultBundle);


        switch (resultBundle.getRefreshType()) {
            case UniversalConstant.REFRESH_ALL: {
                List<Serializable> headerList = new ArrayList<>();
                for (int i = 1000; i < 1010; i++)
                    headerList.add(String.valueOf(i));

                List<Serializable> contentList = new ArrayList<>();
                for (int i = 0; i < 50; i++)
                    contentList.add(String.valueOf(i));

                resultBundle.setHeaderList(headerList);
                resultBundle.setContentList(contentList);
                break;
            }
            case UniversalConstant.REFRESH_CONTENT:
                break;
            case UniversalConstant.REFRESH_PAGE: {
                List<Serializable> contentList = new ArrayList<>();
                for (int i = resultBundle.getPageNum() * 50; i < 50 + resultBundle.getPageNum() * 50; i++)
                    contentList.add(String.valueOf(i));

                resultBundle.setContentList(contentList);
                break;
            }
        }
    }
}
