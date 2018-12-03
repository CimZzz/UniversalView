package com.virtualightning.library.universalview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public View createRootView(Context context, ViewGroup parent) {
        if(rootView != null)
            return rootView;

        rootView = (CustomCoordinatorLayout) LayoutInflater.from(context).inflate(R.layout.view_universal_view_all, parent, false);
        rootView.setAllowRefresh(mediator.isAllowPullRefresh());

        headerView = rootView.findViewById(R.id.header);
        contentView = rootView.findViewById(R.id.contentList);

        //ViewPicker


        //header
        headerAdapter = new HeaderAdapter(mediator, this.mediator.viewPicker);
        headerView.setAdapter(headerAdapter);

        //list
        contentView.setAdapter(contentAdapter = new ContentAdapter(this.mediator, this.mediator.viewPicker));

        if(this.mediator.innerParams.splitDecorationGenerator != null)
            contentView.addItemDecoration(this.mediator.innerParams.splitDecorationGenerator.generateSplitDecoration(context));

        if(this.mediator.innerParams.layoutManagerGenerator != null)
            contentView.setLayoutManager(this.mediator.innerParams.layoutManagerGenerator.generateLayoutManager(this.mediator));
       else {
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            contentView.setLayoutManager(manager);
        }
        return rootView;
    }
}
