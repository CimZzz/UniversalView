package com.virtualightning.universalview;

import com.virtualightning.library.universalview.BaseItemViewCallback;

/**
 * Created by CimZzz on 2018/12/5.<br>
 * Project Name : YIQIMMM<br>
 * Since : YIQIMMM_2.27<br>
 * Description:<br>
 */
public class ItemViewCallbackImpl extends BaseItemViewCallback {
    @Override
    public void onClick(int viewType, Object... args) {
        if((Boolean) args[1])
            getMediator().updateHeaderData((Integer) args[0], viewType,  ((Boolean)args[2]) ? "Hello" : "World!");
        else getMediator().updateContentData((Integer) args[0], ((Boolean)args[2]) ? "Hello" : "World!");
    }

}
