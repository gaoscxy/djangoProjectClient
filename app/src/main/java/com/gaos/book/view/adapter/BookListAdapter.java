package com.gaos.book.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.gaos.book.R;
import com.gaos.book.base.BaseRecyclerAdapter;
import com.gaos.book.model.BookInfo;
import com.gaos.book.view.widget.MyTextView;

import java.util.ArrayList;
import java.util.List;

/**
* 图书列表adapter
* @author gaos
* created at 2021/2/24 14:11
*/
public class BookListAdapter extends BaseRecyclerAdapter<BookInfo> {

    private Context context;

    private LayoutInflater inflater;

    private RecyclerView mRecyclerView;
    private boolean isAllList = false;//ture为追问追打列表页面，不显示*条追问，false为爱心回复详情页面，显示*条追问
    public void setAllList(boolean allList) {
        isAllList = allList;
    }

    private ArrayList<BookInfo> data = new ArrayList<>();

    public BookListAdapter(Context context, List<BookInfo> data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
        } else {
            this.data = new ArrayList<>();
        }
        addDatas(this.data);
    }
    public void updateRes(List<BookInfo> data){
        if (data != null && data.size() != 0) {
            this.data.clear();
            this.data.addAll(data);
            addDatas(this.data);
            notifyDataSetChanged();
        }
    }
    public void addRes(List<BookInfo> data){
        if (data != null && data.size() != 0) {
            this.data.addAll(data);
            addDatas(this.data);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        if (data != null && data.size() != 0) {
            this.data.clear();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, BookInfo data) {
        if (viewHolder instanceof MyHolder) {
            ((MyHolder) viewHolder).name.setText(data.getBook_name());
            ((MyHolder) viewHolder).author.setText(data.getBook_author());
        }
    }

    class MyHolder extends BaseRecyclerAdapter.Holder {
        MyTextView name;
        MyTextView author;


        public MyHolder(View itemView) {
            super(itemView);
            name = (MyTextView) itemView.findViewById(R.id.book_name_tv);
            author = (MyTextView) itemView.findViewById(R.id.book_author_tv);
        }
    }
}
