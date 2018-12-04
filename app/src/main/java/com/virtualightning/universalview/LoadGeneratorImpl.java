package com.virtualightning.universalview;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.virtualightning.library.universalview.interfaces.ILoadingViewGenerator;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class LoadGeneratorImpl implements ILoadingViewGenerator {
    @Override
    public View generateLoadingView(ViewGroup parent) {
        LogUtils.verbose(" <<< Generate Loading");
        TextView textView = new TextView(parent.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setText("Loading!!!");
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(layoutParams);
        return textView;
    }
}
