package com.virtualightning.library.universalview.interfaces;

import com.virtualightning.library.universalview.bases.ResultBundle;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public interface IResultBundleGenerator<T extends ResultBundle> {
    T generateResultBundle();
}
