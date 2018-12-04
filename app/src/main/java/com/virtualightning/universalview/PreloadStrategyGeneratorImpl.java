package com.virtualightning.universalview;

import com.virtualightning.library.universalview.BasePreloadStrategy;
import com.virtualightning.library.universalview.interfaces.IPreloadStrategyGenerator;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class PreloadStrategyGeneratorImpl  implements IPreloadStrategyGenerator {
    @Override
    public BasePreloadStrategy generatePreloadStrategy() {
        return new BasePreloadStrategy() {
            long totalCount;

            @Override
            public void receiverItemCount(int count) {
                totalCount += count;
            }

            @Override
            public void initItemCount(int count) {
                setChecked(false);
                setOver(false);
                totalCount = count;
            }

            @Override
            public boolean checkPreload(int position) {
                return position + 10 >= totalCount;
            }
        };
    }
}
