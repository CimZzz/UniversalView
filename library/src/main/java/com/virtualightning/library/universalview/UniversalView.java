package com.virtualightning.library.universalview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class UniversalView extends FrameLayout {
    private Mediator mediator;

    public UniversalView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UniversalView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /*生命周期方法*/

    private void init(Context context) {
        mediator = new Mediator(context, this);
    }

    /*控件显示方法*/
    void checkState(int viewState) {
        switch (viewState) {
            case InnerState.VIEW_STATE_LOADING:
                removeErrorView();
                removeContentView();
                showLoadingView();
                break;
            case InnerState.VIEW_STATE_ERROR:
                removeLoadingView();
                removeContentView();
                showErrorView();
                break;
            case InnerState.VIEW_STATE_CONTENT:
                removeLoadingView();
                removeErrorView();
                showContentView();
                break;
        }
    }

    private void showLoadingView() {
        if(loadingView == null)
            //init loading view
            loadingView = ViewUtils.inflateView(this, R.layout.view_loading);

        if(loadingView.getParent() != null)
            return;

        addView(loadingView);
    }

    private void removeLoadingView() {
        if(loadingView == null)
            return;

        if(loadingView.getParent() == null)
            return;

        removeView(loadingView);
    }

    private void showErrorView() {
        if(errorView == null)
            //init loading view
            errorView = ViewUtils.inflateView(this, R.layout.view_error);


        if(errorView.getParent() != null)
            return;

        addView(errorView);
    }

    private void removeErrorView() {
        if(errorView == null)
            return;

        if(errorView.getParent() == null)
            return;

        removeView(errorView);
    }

    private void initContentView() {
        if(rootView == null) {
            rootView = ViewUtils.inflateView(this, R.layout.view_goods_content);

            //header
            goodsHeader = rootView.findViewById(R.id.goodsHeader);
            goodsHeader.setAdapter(goodsHeaderAdapter = new GoodsHeaderAdapter(this.goodsVariableState,
                    this.goodsViewPicker,
                    this.goodsAdapterCallback));
            this.goodsVariableState.goodsHeaderAdapter = this.goodsHeaderAdapter;

            //list
            goodsList = rootView.findViewById(R.id.goodsList);
            goodsList.setAdapter(goodsContentAdapter = new GoodsContentAdapter(this.goodsVariableState,
                    this.goodsViewPicker,
                    this.goodsAdapterCallback));
            goodsList.addItemDecoration(new GridSplitDecoration(getContext()));
            GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return goodsContentAdapter.getSpanSize(position);
                }
            });
            goodsList.setLayoutManager(manager);

            this.goodsVariableState.goodsContentAdapter = this.goodsContentAdapter;
        }
    }
}
