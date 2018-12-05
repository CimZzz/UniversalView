package com.virtualightning.library.universalview;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.interfaces.IPreloadStrategyGenerator;
import com.virtualightning.library.universalview.views.CustomCoordinatorLayout;
import com.virtualightning.library.universalview.views.LazyLoadAppbarLayout;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class AllMode implements IViewMode {
    private final Mediator mediator;
    private CustomCoordinatorLayout rootView;
    private LazyLoadAppbarLayout headerView;
    private RecyclerView contentView;

    /*Adapter*/
    private HeaderAdapter headerAdapter;
    private ContentAdapter contentAdapter;


    public AllMode(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void createRootView(Context context, ViewGroup parent) {
        if(rootView != null)
            return;

        rootView = (CustomCoordinatorLayout) LayoutInflater.from(context).inflate(R.layout.view_universal_view_all, parent, false);

        headerView = rootView.findViewById(R.id.header);
        contentView = rootView.findViewById(R.id.contentList);

        //header
        headerAdapter = new HeaderAdapter(mediator, this.mediator.viewPicker);
        headerView.setAdapter(headerAdapter);

        //list
        IPreloadStrategyGenerator preloadStrategyGenerator = this.mediator.preloadStrategyGenerator;
        contentView.setAdapter(contentAdapter = new ContentAdapter(this.mediator, this.mediator.viewPicker,
                preloadStrategyGenerator != null ? preloadStrategyGenerator.generatePreloadStrategy() : null));

        if(this.mediator.splitDecorationGenerator != null)
            contentView.addItemDecoration(this.mediator.splitDecorationGenerator.generateSplitDecoration(context));

        RecyclerView.LayoutManager layoutManager;
        if(this.mediator.layoutManagerGenerator != null)
            layoutManager = this.mediator.layoutManagerGenerator.generateLayoutManager(this.mediator);
       else layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

       if(layoutManager instanceof GridLayoutManager)
           ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
               @Override
               public int getSpanSize(int position) {
                   return mediator.viewPicker.getContentSpanSize(contentAdapter.getItemViewType(position));
               }
           });
       contentView.setLayoutManager(layoutManager);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateHeader() {
        headerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateContent(boolean isOver) {
        contentAdapter.update(isOver);
    }

    @Override
    public void append(int appendCount, boolean isOver) {
        contentAdapter.append(appendCount, isOver);
    }

    @Override
    public void updateHeaderData(int position, int viewType, Object arg, boolean isUpdate) {
        headerAdapter.updateHeaderData(position, viewType, arg, isUpdate);
    }

    @Override
    public void updateContentData(int position, Object arg, boolean isUpdate) {
        contentAdapter.updateContentData(position, arg, isUpdate);
    }

    @Override
    public void expandHeader(boolean isExpand) {
        headerView.setExpanded(isExpand);
    }

    @Override
    public void contentScrollTo(int position) {
        contentView.getLayoutManager().scrollToPosition(position);
    }
}
