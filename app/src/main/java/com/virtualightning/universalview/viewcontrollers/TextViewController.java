package com.virtualightning.universalview.viewcontrollers;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.virtualightning.library.universalview.BaseItemViewCallback;
import com.virtualightning.library.universalview.BaseViewController;
import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class TextViewController extends BaseViewController<String, BaseItemViewCallback> {
    public TextViewController(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View generateRootView(ViewGroup parent) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setTextColor(Color.WHITE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCallback().onClick(getDataType(), getPosition(), true);
            }
        });
        return textView;
    }

    @Override
    protected void initView(ViewGroup parent, HashMap<String, List<View>> shortViewCacheMap) {
    }

    @Override
    protected void onVisible(ViewGroup parent) {
        super.onVisible(parent);
        ((TextView) itemView).setText(getData());
        itemView.setBackgroundColor(Color.RED);
    }

    @Override
    protected void onInvisible(ViewGroup parent) {
        super.onInvisible(parent);
        itemView.setBackgroundColor(Color.GREEN);
    }
}
