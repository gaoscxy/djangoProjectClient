package com.gaos.book.adapter;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.gaos.book.base.adapter.BaseListAdapter;
import com.gaos.book.base.adapter.BaseViewHolder;
import com.gaos.book.base.adapter.IViewHolder;
import com.gaos.book.base.adapter.view.PageStyleHolder;
import com.gaos.book.widget.page.PageStyle;

//import com.example.newbiechen.ireader.ui.adapter.view.PageStyleHolder;
//import com.example.newbiechen.ireader.ui.base.adapter.BaseListAdapter;
//import com.example.newbiechen.ireader.ui.base.adapter.BaseViewHolder;
//import com.example.newbiechen.ireader.ui.base.adapter.IViewHolder;
//import com.example.newbiechen.ireader.widget.page.PageStyle;

/**
 * Created by newbiechen on 17-5-19.
 */

public class PageStyleAdapter extends BaseListAdapter<Drawable> {
    private int currentChecked;

    @Override
    protected IViewHolder<Drawable> createViewHolder(int viewType) {
        return new PageStyleHolder();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        IViewHolder iHolder = ((BaseViewHolder) holder).holder;
        PageStyleHolder pageStyleHolder = (PageStyleHolder) iHolder;
        if (currentChecked == position){
            pageStyleHolder.setChecked();
        }
    }

    public void setPageStyleChecked(PageStyle pageStyle){
        currentChecked = pageStyle.ordinal();
    }

    @Override
    protected void onItemClick(View v, int pos) {
        super.onItemClick(v, pos);
        currentChecked = pos;
        notifyDataSetChanged();
    }
}
