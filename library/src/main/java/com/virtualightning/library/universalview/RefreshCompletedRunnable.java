package com.virtualightning.library.universalview;

import java.lang.ref.WeakReference;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
class RefreshCompletedRunnable implements Runnable {
    private WeakReference<Mediator> mediatorWeakReference;
    private final BaseResultBundle resultBundle;

    RefreshCompletedRunnable(Mediator mediator, BaseResultBundle resultBundle) {
        mediatorWeakReference = new WeakReference<>(mediator);
        this.resultBundle = resultBundle;
    }

    protected Mediator getMediator() {
        return mediatorWeakReference.get();
    }

    @Override
    public void run() {
        Mediator mediator = mediatorWeakReference.get();
        if(mediator == null)
            return;

        if(resultBundle == null)
            return;

        if(mediator.checkCompleted(resultBundle))
            mediator.dataBundle.onRefreshCompleted(resultBundle, mediator);
    }
}
