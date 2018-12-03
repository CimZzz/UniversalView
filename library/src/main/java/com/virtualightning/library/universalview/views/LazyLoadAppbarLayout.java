package com.virtualightning.library.universalview.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.OverScroller;

import com.virtualightning.library.universalview.tools.LazyLoadToolkit;

import java.lang.reflect.Field;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class LazyLoadAppbarLayout extends AppBarLayout implements CoordinatorLayout.AttachedBehavior{
    private LazyLoadToolkit loadToolkit;

    public LazyLoadAppbarLayout(Context context) {
        super(context);
        init();
    }

    public LazyLoadAppbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        loadToolkit = new LazyLoadToolkit(this);
        addOnOffsetChangedListener(new OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                loadToolkit.onVerticalOffsetChanged(-verticalOffset);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        loadToolkit.onLayoutChanged();
    }

    public void setAdapter(LazyLoadToolkit.Adapter adapter) {
        loadToolkit.setAdapter(adapter);
    }

    @NonNull
    @Override
    public CoordinatorLayout.Behavior getBehavior() {
        return new FlingBehavior();
    }


    private static class FlingBehavior extends AppBarLayout.Behavior {
        private OverScroller overScroller;

        public FlingBehavior() {
        }

        @Override
        public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
            if (target instanceof RecyclerView) {
                final RecyclerView recyclerView = (RecyclerView) target;
                consumed = velocityY > 0 || recyclerView.computeVerticalScrollOffset() > 0;
            }
            return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
        }

        @Override
        public void onNestedScrollAccepted(@NonNull CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
            if(overScroller == null) {
                Class<?> cls = getClass().getSuperclass().getSuperclass();
                try {
                    Field field = cls.getDeclaredField("mScroller");
                    field.setAccessible(true);
                    overScroller = (OverScroller) field.get(this);
                    overScroller.abortAnimation();
                } catch (Exception e) {
                }
            }
            else overScroller.abortAnimation();

            super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type);
        }
    }
}

