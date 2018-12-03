package com.virtualightning.library.universalview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.virtualightning.library.universalview.bases.BasePreloadStrategy;
import com.virtualightning.library.universalview.bases.BaseViewPicker;

import java.io.Serializable;

/**
 * Created by CimZzz on 2018/12/3.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Mediator mediator;
    private final BaseViewPicker viewPicker;
    private final BasePreloadStrategy preloadStrategy;

    public ContentAdapter(Mediator mediator, BaseViewPicker viewPicker, BasePreloadStrategy preloadStrategy) {
        this.mediator = mediator;
        this.viewPicker = viewPicker;
        this.preloadStrategy = preloadStrategy;
    }


    /* Implementation */

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return this.viewPicker.onContentCreate(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        this.viewPicker.onContentBind(holder, viewType, getData(position));
        if(this.preloadStrategy != null)
            this.preloadStrategy.checkPreload(position);
    }

    @Override
    public int getItemCount() {
        if(this.viewPicker == null)
            return 0;

        return this.mediator.innerDataBundle.contentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.viewPicker.getContentViewType(position, getData(position));
    }

    private Serializable getData(int position) {
        return position >= getItemCount() - 1 ? null : this.mediator.innerDataBundle.contentList.get(position);
    }

}
