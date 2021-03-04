package com.gaos.book.base.adapter.view;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.gaos.book.R;
import com.gaos.book.base.adapter.ViewHolderImpl;
import com.gaos.book.common.GlobalConstant;
import com.gaos.book.utils.BookManager;
import com.gaos.book.view.widget.page.TxtChapter;

import java.util.Objects;


/**
 * Created by newbiechen on 17-5-16.
 */

public class CategoryHolder extends ViewHolderImpl<TxtChapter> {

    private TextView mTvChapter;

    @Override
    public void initView() {
        mTvChapter = findById(R.id.category_tv_chapter);
        try {
            AssetManager mgr = Objects.requireNonNull(getContext()).getAssets();
            //根据路径得到Typeface
            Typeface tf = Typeface.createFromAsset(mgr, "fonts/" + GlobalConstant.FONT_STYLE);
            mTvChapter.setTypeface(tf);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBind(TxtChapter value, int pos){
        //首先判断是否该章已下载
        Drawable drawable = null;

        //TODO:目录显示设计的有点不好，需要靠成员变量是否为null来判断。
        //如果没有链接地址表示是本地文件
        if (value.getLink() == null){
            drawable = getContext().getResources().getDrawable(R.drawable.selector_category_load,null);
        }
        else {
            if (BookManager.isChapterCached(value.getBookId(),value.getTitle())){
                drawable = getContext().getResources().getDrawable(R.drawable.selector_category_load,null);
            }else {
                drawable = getContext().getResources().getDrawable(R.drawable.selector_category_unload,null);
            }
        }

        mTvChapter.setSelected(false);
        mTvChapter.setTextColor(getContext().getResources().getColor(R.color.nb_text_default));
        mTvChapter.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        mTvChapter.setText(value.getTitle());
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_category;
    }

    public void setSelectedChapter(){
        mTvChapter.setTextColor(getContext().getResources().getColor(R.color.light_red));
        mTvChapter.setSelected(true);
    }
}
