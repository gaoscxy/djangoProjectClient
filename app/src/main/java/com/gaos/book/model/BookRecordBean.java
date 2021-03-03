package com.gaos.book.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by newbiechen on 17-5-20.
 */
@Entity
public class BookRecordBean{
    //所属的书的id
    @Id
    private int bookId;
    //阅读到了第几章
    private int chapter;
    //当前的页码
    private int pagePos;

    @Generated(hash = 831597990)
    public BookRecordBean(int bookId, int chapter, int pagePos) {
        this.bookId = bookId;
        this.chapter = chapter;
        this.pagePos = pagePos;
    }

    @Generated(hash = 398068002)
    public BookRecordBean() {
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getPagePos() {
        return pagePos;
    }

    public void setPagePos(int pagePos) {
        this.pagePos = pagePos;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
