package com.virtualightning.library.universalview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        rootView.setAllowRefresh(mediator.isAllowPullRefresh());

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

        if(this.mediator.layoutManagerGenerator != null)
            contentView.setLayoutManager(this.mediator.layoutManagerGenerator.generateLayoutManager(this.mediator));
       else {
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            contentView.setLayoutManager(manager);
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void updateHeaderData() {
        headerAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateContentData() {
        contentAdapter.update();
    }

    @Override
    public void append(int appendCount, boolean isOver) {
        contentAdapter.append(appendCount, isOver);
    }

    @Override
    public void updateHeaderData(int position, int viewType, Object arg) {
        headerAdapter.updateHeaderData(position, viewType, arg);
    }

    @Override
    public void updateContentData(int position, Object arg) {
        contentAdapter.updateContentData(position, arg);
    }
}
