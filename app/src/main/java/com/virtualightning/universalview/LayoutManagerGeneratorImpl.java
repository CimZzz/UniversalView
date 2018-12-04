package com.virtualightning.universalview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.virtualightning.library.universalview.Mediator;
import com.virtualightning.library.universalview.interfaces.ILayoutManagerGenerator;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class LayoutManagerGeneratorImpl implements ILayoutManagerGenerator {
    @Override
    public RecyclerView.LayoutManager generateLayoutManager(Mediator mediator) {
        return new GridLayoutManager(mediator.getContext(), 2);
    }
}
