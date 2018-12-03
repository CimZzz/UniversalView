package com.virtualightning.library.universalview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class LazyLoadLinearLayout extends ScrollView {
    private LazyLoadToolkit loadToolkit;

    public LazyLoadLinearLayout(Context context) {
        super(context);
        init();
    }

    public LazyLoadLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LazyLoadLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LinearLayout childView = new LinearLayout(getContext()) {
            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                super.onLayout(changed, l, t, r, b);
                loadToolkit.onLayoutChanged();
            }
        };
        childView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        childView.setOrientation(LinearLayout.VERTICAL);
        addView(childView);
        loadToolkit = new LazyLoadToolkit(childView);
    }


    public void setAdapter(LazyLoadToolkit.Adapter adapter) {
        loadToolkit.setAdapter(adapter);
    }

    public void removeDataAt(int position) {
        loadToolkit.removeDataAt(position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        loadToolkit.onVerticalOffsetChanged(getScrollY());
    }

}
