package com.virtualightning.library.universalview;

import android.view.ViewGroup;

import com.virtualightning.library.universalview.bases.BaseViewPicker;
import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class HeaderAdapter extends LazyLoadToolkit.Adapter{
    private final Mediator mediator;
    private final BaseViewPicker viewPicker;

    public HeaderAdapter(Mediator mediator, BaseViewPicker viewPicker) {
        this.mediator = mediator;
        this.viewPicker = viewPicker;
    }



    /* Implementation */

    @Override
    public Object getData(int position) {
        return this.mediator.innerDataBundle.headerList.get(position);
    }

    @Override
    public int getItemCount() {
        if(this.viewPicker == null)
            return 0;

        return this.mediator.innerDataBundle.headerList.size();
    }

    @Override
    public int getItemDataType(Object data, int position) {
        return viewPicker.getHeaderViewType(data, position);
    }

    @Override
    public LazyLoadToolkit.ViewController createViewController(ViewGroup parent, int position, int dataType) {
        return viewPicker.onHeaderCreate(parent, position, dataType);
    }
}
