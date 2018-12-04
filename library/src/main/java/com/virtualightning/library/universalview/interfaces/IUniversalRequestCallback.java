package com.virtualightning.library.universalview.interfaces;

import com.virtualightning.library.universalview.BaseDataBundle;
import com.virtualightning.library.universalview.BaseResultBundle;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public interface IUniversalRequestCallback {
    BaseResultBundle generateResultBundle();

    void doRequest(BaseResultBundle resultBundle);

    void cancelRequest(int refreshType);

    void handleResultBundle(BaseResultBundle resultBundle, BaseDataBundle dataBundle);
}
