package com.virtualightning.library.universalview;

import com.virtualightning.library.universalview.Mediator;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public abstract class BasePreloadStrategy {
    private boolean isChecked;
    private boolean isOver;
    private ICallback callback;

    public final void setCallback(ICallback callback) {
        this.callback = callback;
    }

    public final void check(int position) {
        if(isChecked || isOver)
            return;

        if(checkPreload(position)) {
            setChecked(true);
            callback.onPreload();
        }
    }

    public final void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public final void setOver(boolean isOver) {
        this.isOver = isOver;
    }

    public abstract void receiverItemCount(int count);
    public abstract void initItemCount(int count);
    public abstract boolean checkPreload(int position);

    public interface ICallback {
        void onPreload();
    }
}
