package com.gaos.book.model;

import java.io.Serializable;

@Entity
public class CatalogInfo implements Serializable {
    private int chapter_id;
    private String chapter_name;
    private String chapter_path;
    @Id
    private String bookinfo_id;

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getChapter_path() {
        return chapter_path;
    }

    public void setChapter_path(String chapter_path) {
        this.chapter_path = chapter_path;
    }

    public String getBookinfo_id() {
        return bookinfo_id;
    }

    public void setBookinfo_id(String bookinfo_id) {
        this.bookinfo_id = bookinfo_id;
    }
}
