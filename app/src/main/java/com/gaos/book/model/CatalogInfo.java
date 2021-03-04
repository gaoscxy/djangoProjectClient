package com.gaos.book.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;


@Entity
public class CatalogInfo  implements Parcelable {
    @Id
    private Long chapter_id;
    private String chapter_name;
    private String chapter_path;
    @Index
    private Long bookinfo_id;
    //章节内容在文章中的起始位置(本地)
    long start;
    //章节内容在文章中的终止位置(本地)
    long end;
    private boolean unreadble;

    public boolean isUnreadble() {
        return unreadble;
    }

    public void setUnreadble(boolean unreadble) {
        this.unreadble = unreadble;
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

    public Long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(Long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public Long getBookinfo_id() {
        return bookinfo_id;
    }

    public void setBookinfo_id(Long bookinfo_id) {
        this.bookinfo_id = bookinfo_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.chapter_id);
        dest.writeString(this.chapter_name);
        dest.writeString(this.chapter_path);
        dest.writeLong(this.bookinfo_id);
    }

    public boolean getUnreadble() {
        return this.unreadble;
    }

    protected CatalogInfo(Parcel in) {
        this.chapter_id = in.readLong();
        this.chapter_name = in.readString();
        this.chapter_path = in.readString();
        this.bookinfo_id = in.readLong();
    }

    @Generated(hash = 17520835)
    public CatalogInfo(Long chapter_id, String chapter_name, String chapter_path,
            Long bookinfo_id, long start, long end, boolean unreadble) {
        this.chapter_id = chapter_id;
        this.chapter_name = chapter_name;
        this.chapter_path = chapter_path;
        this.bookinfo_id = bookinfo_id;
        this.start = start;
        this.end = end;
        this.unreadble = unreadble;
    }

    @Generated(hash = 1044076522)
    public CatalogInfo() {
    }


    public static final Creator<CatalogInfo> CREATOR = new Creator<CatalogInfo>() {
        @Override
        public CatalogInfo createFromParcel(Parcel source) {
            return new CatalogInfo(source);
        }

        @Override
        public CatalogInfo[] newArray(int size) {
            return new CatalogInfo[size];
        }
    };
}
