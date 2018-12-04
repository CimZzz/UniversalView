package com.virtualightning.library.universalview.tools;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class MainUIThread {
    private static Handler uiHandler;

    public static void init() {
        if(uiHandler == null) {
            uiHandler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                }
            };
        }
    }

    public static void postRunnable(Runnable runnable) {
        init();
        uiHandler.post(runnable);
    }

    public static void postRunnableDelay(Runnable runnable, long delay) {
        init();
        uiHandler.postDelayed(runnable, delay);
    }
}
