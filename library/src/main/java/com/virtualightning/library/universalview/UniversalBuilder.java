package com.virtualightning.library.universalview;

import com.virtualightning.library.universalview.interfaces.IErrorViewGenerator;
import com.virtualightning.library.universalview.interfaces.ILoadingViewGenerator;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalBuilder {
    IErrorViewGenerator errorViewGenerator;
    ILoadingViewGenerator loadingViewGenerator;

    public UniversalBuilder errorViewGenerator(IErrorViewGenerator errorViewGenerator) {
        this.errorViewGenerator = errorViewGenerator;
        return this;
    }

    public UniversalBuilder loadingViewGenerator(ILoadingViewGenerator loadingViewGenerator) {
        this.loadingViewGenerator = loadingViewGenerator;
        return this;
    }
}
