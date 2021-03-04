package com.gaos.book.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.gaos.book.model.gen.DaoSession;
import com.gaos.book.model.gen.CatalogInfoDao;
import com.gaos.book.model.gen.BookInfoDao;

@Entity
public class BookInfo implements Parcelable {
    @Id
    private Long book_id;
    private String book_name;
    private String book_author;
    private String book_introduce;
    private String book_update_time;
    //是否更新或未阅读
    private boolean isUpdate = true;
    //是否是本地文件
    private boolean isLocal = false;

    //章节内容在文章中的起始位置(本地)
    long start;
    //章节内容在文章中的终止位置(本地)
    long end;
    //最新阅读日期
    private String lastRead;
    private int chaptersCount;
    private String lastChapter;
    private String cover; // 在本地书籍中，该字段作为本地文件的路径
    //最新更新日期
    private String updated;

    @ToMany(referencedJoinProperty = "bookinfo_id")
    private List<CatalogInfo> bookChapterList;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_introduce() {
        return book_introduce;
    }

    public void setBook_introduce(String book_introduce) {
        this.book_introduce = book_introduce;
    }

    public String getBook_update_time() {
        return book_update_time;
    }

    public void setBook_update_time(String book_update_time) {
        this.book_update_time = book_update_time;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
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

    public String getLastRead() {
        return lastRead;
    }

    public void setLastRead(String lastRead) {
        this.lastRead = lastRead;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public void setBookChapterList(List<CatalogInfo> bookChapterList) {
        this.bookChapterList = bookChapterList;
    }
    public List<CatalogInfo> getBookChapters(){
        if (daoSession == null){
            return bookChapterList;
        }
        else {
            return getBookChapterList();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(this.book_id);
        dest.writeString(this.book_name);
        dest.writeString(this.book_author);
        dest.writeString(this.book_introduce);
        dest.writeString(this.book_update_time);
        dest.writeByte(this.isUpdate ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isLocal ? (byte) 1 : (byte) 0);
        dest.writeString(this.lastRead);
        dest.writeInt(this.chaptersCount);
        dest.writeString(this.lastChapter);
    }

    public boolean getIsUpdate() {
        return this.isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean getIsLocal() {
        return this.isLocal;
    }

    public void setIsLocal(boolean isLocal) {
        this.isLocal = isLocal;
    }


    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1077762221)
    public synchronized void resetBookChapterList() {
        bookChapterList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 472309544)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookInfoDao() : null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1412780128)
    public List<CatalogInfo> getBookChapterList() {
        if (bookChapterList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CatalogInfoDao targetDao = daoSession.getCatalogInfoDao();
            List<CatalogInfo> bookChapterListNew = targetDao._queryBookInfo_BookChapterList(book_id);
            synchronized (this) {
                if (bookChapterList == null) {
                    bookChapterList = bookChapterListNew;
                }
            }
        }
        return bookChapterList;
    }

    protected BookInfo(Parcel in) {
        this.book_id = in.readLong();
        this.book_name = in.readString();
        this.book_author = in.readString();
        this.book_introduce = in.readString();
        this.book_update_time = in.readString();

        this.isUpdate = in.readByte() != 0;
        this.isLocal = in.readByte() != 0;
        this.lastRead = in.readString();
        this.chaptersCount = in.readInt();
        this.lastChapter = in.readString();
    }

    @Generated(hash = 1508333171)
    public BookInfo(Long book_id, String book_name, String book_author, String book_introduce,
            String book_update_time, boolean isUpdate, boolean isLocal, long start, long end,
            String lastRead, int chaptersCount, String lastChapter, String cover, String updated) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_introduce = book_introduce;
        this.book_update_time = book_update_time;
        this.isUpdate = isUpdate;
        this.isLocal = isLocal;
        this.start = start;
        this.end = end;
        this.lastRead = lastRead;
        this.chaptersCount = chaptersCount;
        this.lastChapter = lastChapter;
        this.cover = cover;
        this.updated = updated;
    }

    @Generated(hash = 1952025412)
    public BookInfo() {
    }

    public static final Creator<BookInfo> CREATOR = new Creator<BookInfo>() {
        @Override
        public BookInfo createFromParcel(Parcel source) {
            return new BookInfo(source);
        }

        @Override
        public BookInfo[] newArray(int size) {
            return new BookInfo[size];
        }
    };
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 56468943)
    private transient BookInfoDao myDao;
}
