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


    public void updateHeaderData(int position, int viewType, Object arg) {
        if(this.mediator.dataUpdateVisitor == null)
            return;

        int itemCount = getItemCount();
        if(position != -1) {
            if(position >= itemCount)
                return;

            Object data = getData(position);

            if(viewType != getItemDataType(data, position))
                return;

            LazyLoadToolkit.ViewController<?> controller = getViewController(position);
            if(controller == null)
                return;

            this.mediator.dataUpdateVisitor.update(viewType, data, arg);
            notifyDataChangedAt(controller);
        }
        else {
            for(int i = 0 ; i < itemCount ; i ++) {
                Object data = getData(position);

                if(viewType == getItemDataType(data, position)) {
                    LazyLoadToolkit.ViewController<?> controller = getViewController(position);
                    if(controller == null)
                        return;

                    this.mediator.dataUpdateVisitor.update(viewType, data, arg);
                    notifyDataChangedAt(controller);
                    return;
                }
            }
        }
    }


    /* Implementation */

    @Override
    public Object getData(int position) {
        return this.mediator.dataBundle.headerList.get(position);
    }

    @Override
    public int getItemCount() {
        if(this.viewPicker == null)
            return 0;

        return this.mediator.dataBundle.headerList.size();
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
