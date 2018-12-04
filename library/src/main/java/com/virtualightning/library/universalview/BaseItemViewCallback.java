package com.virtualightning.library.universalview;

import com.virtualightning.library.universalview.interfaces.IItemViewCallback;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class BaseItemViewCallback implements IItemViewCallback {
    Mediator mediator;

    public Mediator getMediator() {
        return mediator;
    }

    @Override
    public void onClick(int viewType, Object... args) {

    }
}
