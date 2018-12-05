package com.virtualightning.universalview.viewholders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.virtualightning.library.universalview.BaseItemViewCallback;
import com.virtualightning.library.universalview.BaseViewHolder;

/**
 * Created by CimZzz on 2018/12/4.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class TextViewHolder extends BaseViewHolder<BaseItemViewCallback> {

    public TextViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
        TextView textView = (TextView) itemView;
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = getMediator().getValue("key", true);
                getMediator().setValue("key", !check);
                getCallback().onClick(getItemViewType(), getAdapterPosition(), false, check);
            }
        });
    }

    public void bindData(String data) {
        TextView textView = (TextView) itemView;
        textView.setText(data);
    }
}
