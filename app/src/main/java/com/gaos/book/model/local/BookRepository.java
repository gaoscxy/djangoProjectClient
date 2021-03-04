package com.gaos.book.model.local;

import android.util.Log;


import com.gaos.book.model.BookInfo;
import com.gaos.book.model.BookRecordBean;
import com.gaos.book.model.CatalogInfo;
import com.gaos.book.model.gen.BookInfoDao;
import com.gaos.book.model.gen.BookRecordBeanDao;
import com.gaos.book.model.gen.CatalogInfoDao;
import com.gaos.book.model.gen.DaoSession;
import com.gaos.book.utils.BookManager;
import com.gaos.book.utils.Constant;
import com.gaos.book.utils.FileUtils;
import com.gaos.book.utils.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

/**
 * Created by newbiechen on 17-5-8.
 * 存储关于书籍内容的信息(CollBook(收藏书籍),BookChapter(书籍列表),ChapterInfo(书籍章节),BookRecord(记录))
 */

public class BookRepository {
    private static final String TAG = "CollBookManager";
    private static volatile BookRepository sInstance;
    private DaoSession mSession;
    private BookInfoDao mCollBookDao;
    private CatalogInfoDao mCatalogInfoDao;
    private BookRepository(){
        mSession = DaoDbHelper.getInstance()
                .getSession();
        mCollBookDao = mSession.getBookInfoDao();
    }

    public static BookRepository getInstance(){
        if (sInstance == null){
            synchronized (BookRepository.class){
                if (sInstance == null){
                    sInstance = new BookRepository();
                }
            }
        }
        return sInstance;
    }

    //存储已收藏书籍
    public void saveCollBookWithAsync(BookInfo bean){
        //启动异步存储
        mSession.startAsyncSession()
                .runInTx(
                        () -> {
                            if (bean.getBookChapterList() != null){
                                // 存储BookChapterBean
                                mSession.getCatalogInfoDao()
                                        .insertOrReplaceInTx(bean.getBookChapters());
                            }
                            //存储CollBook (确保先后顺序，否则出错)
                            mCollBookDao.insertOrReplace(bean);
                        }
                );
    }
    /**
     * 异步存储。
     * 同时保存BookChapter
     * @param beans
     */
//    public void saveCollBooksWithAsync(List<CollBookBean> beans){
//        mSession.startAsyncSession()
//                .runInTx(
//                        () -> {
//                            for (CollBookBean bean : beans){
//                                if (bean.getBookChapters() != null){
//                                    //存储BookChapterBean(需要修改，如果存在id相同的则无视)
//                                    mSession.getBookChapterBeanDao()
//                                            .insertOrReplaceInTx(bean.getBookChapters());
//                                }
//                            }
//                            //存储CollBook (确保先后顺序，否则出错)
//                            mCollBookDao.insertOrReplaceInTx(beans);
//                        }
//                );
//    }

    public void saveCollBook(BookInfo bean){
        mCollBookDao.insertOrReplace(bean);
    }

    public void saveCollBooks(List<BookInfo> beans){
        mCollBookDao.insertOrReplaceInTx(beans);
    }

    /**
     * 异步存储BookChapter
     * @param beans
     */
    public void saveBookChaptersWithAsync(List<CatalogInfo> beans){
        mSession.startAsyncSession()
                .runInTx(
                        () -> {
                            //存储BookChapterBean
                            mSession.getCatalogInfoDao()
                                    .insertOrReplaceInTx(beans);
                            Log.d(TAG, "saveBookChaptersWithAsync: "+"进行存储");
                        }
                );
    }

    /**
     * 存储章节
     * @param bookId
     * @param fileName
     * @param content
     */
    public void saveChapterInfo(long bookId,String fileName,String content){
        File file = BookManager.getBookFile(bookId, fileName);
        //获取流并存储
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.close(writer);
        }
    }

    public void saveBookRecord(BookRecordBean bean){
        mSession.getBookRecordBeanDao()
                .insertOrReplace(bean);
    }

    /*****************************get************************************************/
    public BookInfo getCollBook(long bookId){
        BookInfo bean = mCollBookDao.queryBuilder()
                .where(BookInfoDao.Properties.Book_id.eq(bookId))
                .unique();
        return bean;
    }


    public  List<BookInfo> getCollBooks(){
        return mCollBookDao
                .queryBuilder()
                .orderDesc(BookInfoDao.Properties.LastRead)
                .list();
    }


    //获取书籍列表
    public Single<List<CatalogInfo>> getBookChaptersInRx(long bookId){
        return Single.create(new SingleOnSubscribe<List<CatalogInfo>>() {
            @Override
            public void subscribe(SingleEmitter<List<CatalogInfo>> e) throws Exception {
                List<CatalogInfo> beans = mSession
                        .getCatalogInfoDao()
                        .queryBuilder()
                        .where(CatalogInfoDao.Properties.Bookinfo_id.eq(bookId))
                        .list();
                e.onSuccess(beans);
            }
        });
    }

    //获取阅读记录
    public BookRecordBean getBookRecord(long bookId){
        return mSession.getBookRecordBeanDao()
                .queryBuilder()
                .where(BookRecordBeanDao.Properties.BookId.eq(bookId))
                .unique();
    }

    //TODO:需要进行获取编码并转换的问题
//        public ChapterInfoBean getChapterInfoBean(String folderName, String fileName){
//        File file = new File(Constant.BOOK_CACHE_PATH + folderName
//                + File.separator + fileName + FileUtils.SUFFIX_NB);
//        if (!file.exists()) return null;
//        Reader reader = null;
//        String str = null;
//        StringBuilder sb = new StringBuilder();
//        try {
//            reader = new FileReader(file);
//            BufferedReader br = new BufferedReader(reader);
//            while ((str = br.readLine()) != null){
//                sb.append(str);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            IOUtils.close(reader);
//        }
//
//        ChapterInfoBean bean = new ChapterInfoBean();
//        bean.setTitle(fileName);
//        bean.setBody(sb.toString());
//        return bean;
//    }

    /************************************************************/

    /************************************************************/
    public Single<java.lang.Void> deleteCollBookInRx(BookInfo bean) {
        return Single.create(new SingleOnSubscribe<java.lang.Void>() {
            @Override
            public void subscribe(SingleEmitter<java.lang.Void> e) throws Exception {
                //查看文本中是否存在删除的数据
                deleteBook(bean.getBook_id());
                //删除任务
//                deleteDownloadTask(bean.get_id());
                //删除目录
                deleteBookChapter(bean.getBook_id());
                //删除CollBook
                mCollBookDao.delete(bean);
//                e.onSuccess(new java.lang.Void());
            }
        });
    }

    //这个需要用rx，进行删除
    public void deleteBookChapter(long bookId){
        mSession.getBookInfoDao()
                .queryBuilder()
                .where(BookInfoDao.Properties.Book_id.eq(bookId))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    public void deleteCollBook(BookInfo collBook){
        mCollBookDao.delete(collBook);
    }

    //删除书籍
    public void deleteBook(long bookId){
        FileUtils.deleteFile(Constant.BOOK_CACHE_PATH+bookId);
    }

    public void deleteBookRecord(String id){
        mSession.getBookRecordBeanDao()
                .queryBuilder()
                .where(BookRecordBeanDao.Properties.BookId.eq(id))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    //删除任务
//    public void deleteDownloadTask(int bookId){
//        mSession.getDownloadTaskBeanDao()
//                .queryBuilder()
//                .where(DownloadTaskBeanDao.Properties.BookId.eq(bookId))
//                .buildDelete()
//                .executeDeleteWithoutDetachingEntities();
//    }

    public DaoSession getSession(){
        return mSession;
    }
}
