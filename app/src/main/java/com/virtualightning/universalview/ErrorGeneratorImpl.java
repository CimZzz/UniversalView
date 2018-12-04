package com.virtualightning.universalview;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.virtualightning.library.universalview.interfaces.IErrorViewGenerator;

import java.util.Random;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ErrorGeneratorImpl implements IErrorViewGenerator {
    private final View.OnClickListener clickListener;

    public ErrorGeneratorImpl(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public View generateErrorView(ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        textView.setText("Error!!!!");
        textView.setTextColor(Color.WHITE);
        textView.setLayoutParams(layoutParams);
        textView.setOnClickListener(clickListener);
        return textView;
    }
}
