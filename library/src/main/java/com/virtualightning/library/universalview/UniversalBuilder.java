package com.virtualightning.library.universalview;

import com.virtualightning.library.universalview.interfaces.IErrorViewGenerator;
import com.virtualightning.library.universalview.interfaces.IItemViewCallback;
import com.virtualightning.library.universalview.interfaces.ILayoutManagerGenerator;
import com.virtualightning.library.universalview.interfaces.ILoadingViewGenerator;
import com.virtualightning.library.universalview.interfaces.IPreloadStrategyGenerator;
import com.virtualightning.library.universalview.interfaces.ISplitDecorationGenerator;
import com.virtualightning.library.universalview.interfaces.IUniversalRequestCallback;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalBuilder {
    IErrorViewGenerator errorViewGenerator;
    ILoadingViewGenerator loadingViewGenerator;

    ISplitDecorationGenerator splitDecorationGenerator;
    IUniversalRequestCallback universalRequestCallback;
    ILayoutManagerGenerator layoutManagerGenerator;
    IPreloadStrategyGenerator preloadStrategyGenerator;
    IItemViewCallback itemViewCallback;
    BaseViewPicker viewPicker;
    BaseDataBundle dataBundle;

    public UniversalBuilder errorViewGenerator(IErrorViewGenerator errorViewGenerator) {
        this.errorViewGenerator = errorViewGenerator;
        return this;
    }

    public UniversalBuilder loadingViewGenerator(ILoadingViewGenerator loadingViewGenerator) {
        this.loadingViewGenerator = loadingViewGenerator;
        return this;
    }

    public UniversalBuilder splitDecorationGenerator(ISplitDecorationGenerator splitDecorationGenerator) {
        this.splitDecorationGenerator = splitDecorationGenerator;
        return this;
    }

    public UniversalBuilder universalRequestCallback(IUniversalRequestCallback universalRequestCallback) {
        this.universalRequestCallback = universalRequestCallback;
        return this;
    }

    public UniversalBuilder layoutManagerGenerator(ILayoutManagerGenerator layoutManagerGenerator) {
        this.layoutManagerGenerator = layoutManagerGenerator;
        return this;
    }

    public UniversalBuilder preloadStrategyGenerator(IPreloadStrategyGenerator preloadStrategyGenerator) {
        this.preloadStrategyGenerator = preloadStrategyGenerator;
        return this;
    }

    public UniversalBuilder itemViewCallback(IItemViewCallback itemViewCallback) {
        this.itemViewCallback = itemViewCallback;
        return this;
    }

    public UniversalBuilder viewPicker(BaseViewPicker viewPicker) {
        this.viewPicker = viewPicker;
        return this;
    }

    public UniversalBuilder dataBundle(BaseDataBundle dataBundle) {
        this.dataBundle = dataBundle;
        return this;
    }

    boolean checkInit() {
        return this.errorViewGenerator != null &&
                this.loadingViewGenerator != null &&
                this.universalRequestCallback != null &&
                this.viewPicker != null &&
                this.itemViewCallback != null &&
                this.dataBundle != null;
    }
}
