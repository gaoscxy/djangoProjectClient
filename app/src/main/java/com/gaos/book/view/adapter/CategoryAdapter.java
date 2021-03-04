package com.gaos.book.view.adapter;

import android.view.View;
import android.view.ViewGroup;


import com.gaos.book.base.adapter.EasyAdapter;
import com.gaos.book.base.adapter.IViewHolder;
import com.gaos.book.base.adapter.view.CategoryHolder;
import com.gaos.book.view.widget.page.TxtChapter;

/**
*
* @author gaos
* created at 2021/2/26 9:40
*/
public class CategoryAdapter extends EasyAdapter<TxtChapter> {
    private int currentSelected = 0;
    @Override
    protected IViewHolder<TxtChapter> onCreateViewHolder(int viewType) {
        return new CategoryHolder();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        CategoryHolder holder = (CategoryHolder) view.getTag();

        if (position == currentSelected){
            holder.setSelectedChapter();
        }

        return view;
    }

    public void setChapter(int pos){
        currentSelected = pos;
        notifyDataSetChanged();
    }
}
