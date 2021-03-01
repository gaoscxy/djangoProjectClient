package com.gaos.book.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by newbiechen on 17-5-10.
 * 书的章节链接(作为下载的进度数据)
 * 同时作为网络章节和本地章节 (没有找到更好分离两者的办法)
 */
@Entity
public class BookChapterBean implements Serializable{
    private static final long serialVersionUID = 56423411313L;

    /**
     * title : 第一章 他叫白小纯
     * link : http://read.qidian.com/chapter/rJgN8tJ_cVdRGoWu-UQg7Q2/6jr-buLIUJSaGfXRMrUjdw2
     * unreadble : false
     */
    @Id
    private String id;

//    @Id
//    private int chapter_id;

    private String chapter_path;

    private String chapter_name;

    //所属的下载任务
    private String taskName;

    private boolean unreadble;

    //所属的书籍
    @Index
    private int bookinfo_id;

    //本地书籍参数


    //在书籍文件中的起始位置
    private long start;

    //在书籍文件中的终止位置
    private long end;



    @Generated(hash = 1820472629)
    public BookChapterBean(String id, String chapter_path, String chapter_name,
            String taskName, boolean unreadble, int bookinfo_id, long start, long end) {
        this.id = id;
        this.chapter_path = chapter_path;
        this.chapter_name = chapter_name;
        this.taskName = taskName;
        this.unreadble = unreadble;
        this.bookinfo_id = bookinfo_id;
        this.start = start;
        this.end = end;
    }

    @Generated(hash = 853839616)
    public BookChapterBean() {
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter_path() {
        return chapter_path;
    }

    public void setChapter_path(String chapter_path) {
        this.chapter_path = chapter_path;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isUnreadble() {
        return unreadble;
    }

    public void setUnreadble(boolean unreadble) {
        this.unreadble = unreadble;
    }

    public int getBookinfo_id() {
        return bookinfo_id;
    }

    public void setBookinfo_id(int bookinfo_id) {
        this.bookinfo_id = bookinfo_id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "BookChapterBean{" +
                "id='" + id + '\'' +
                ", chapter_path='" + chapter_path + '\'' +
                ", chapter_name='" + chapter_name + '\'' +
                ", taskName='" + taskName + '\'' +
                ", unreadble=" + unreadble +
                ", bookinfo_id=" + bookinfo_id +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public boolean getUnreadble() {
        return this.unreadble;
    }
}