package com.virtualightning.library.universalview.interfaces;

import com.virtualightning.library.universalview.Mediator;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public interface IItemViewCallback {
    Mediator getMediator();

    void onClick(int viewType, Object... args);
}
