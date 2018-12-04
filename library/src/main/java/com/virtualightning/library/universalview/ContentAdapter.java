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

    public void update() {
        int count = getItemCount();
        this.preloadStrategy.setChecked(false);
        this.preloadStrategy.setOver(false);
        if(count == 0)
            this.preloadStrategy.setOver(true);
        else this.preloadStrategy.initItemCount(count);
        notifyDataSetChanged();
    }

    public void append(int appendCount, boolean isOver) {
        if(isOver)
            this.preloadStrategy.setOver(true);
        if(appendCount != -1)
            notifyItemRangeInserted(this.mediator.dataBundle.contentList.size(), appendCount);
    }

    public void updateContentData(int position, Object arg) {
        if(this.mediator.
                dataUpdateVisitor == null)
            return;

        if(position == -1 || position >= getItemCount())
            return;

        this.mediator.dataUpdateVisitor.update(getItemViewType(position), getData(position), arg);
        notifyItemChanged(position);
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

        return this.mediator.dataBundle.contentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.viewPicker.getContentViewType(position, getData(position));
    }

    private Serializable getData(int position) {
        return position >= getItemCount() - 1 ? null : this.mediator.dataBundle.contentList.get(position);
    }
}
