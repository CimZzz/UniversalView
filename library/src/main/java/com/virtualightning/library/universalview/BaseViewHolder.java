package com.virtualightning.library.universalview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.virtualightning.library.universalview.interfaces.IItemViewCallback;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public abstract class BaseViewHolder<E extends IItemViewCallback> extends RecyclerView.ViewHolder {
    E callback;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected E getCallback() {
        return this.callback;
    }

    protected Mediator getMediator() {return this.callback.getMediator(); }
}
