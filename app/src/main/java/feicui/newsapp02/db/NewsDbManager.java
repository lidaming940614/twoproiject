package feicui.newsapp02.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class NewsDbManager {
    SQLiteDatabase sqL;
    Context mC;
    NewsDB newsDB;

    private NewsDbManager(Context mC) {
        this.mC = mC;

        if (newsDB == null) {
            newsDB = new NewsDB(mC);
            sqL = newsDB.getReadableDatabase();
        }
    }

    private static NewsDbManager newsDbManager;

    public static NewsDbManager getDBManger(Context mC) {
        if (newsDbManager == null) {
            newsDbManager = new NewsDbManager(mC);

        }
        return newsDbManager;
    }

    /**
     * 保存信息和收藏
     *
     * @param dbname   要保存到的数据库的表名
     * @param imgurl   图片url地址
     * @param newsname 新闻名称
     * @param newsurl  新闻url地址
     * @param newsdate 新闻时间
     * @param newstype 新闻类型
     */
    public void savenews(String dbname, String imgurl, String newsname, String newsurl, String newsdate, String newstype) {
        ContentValues cv = new ContentValues();
        cv.put("imgurl", imgurl);
        cv.put("newsname", newsname);
        cv.put("newsurl", newsurl);
        cv.put("newsdate", newsdate);
        cv.put("newstype", newstype);
        sqL.insert(dbname, null, cv);
    }

    /**
     * 保存标签状态
     *
     * @param tabname
     */
    public void savetab(String tabname) {
        ContentValues cv = new ContentValues();
        cv.put("tabname", tabname);
        sqL.insert("tab", null, cv);
    }

    /**
     * 删除表的全部内容
     *
     * @param dbname 需要删除的表名
     */
    public void delectnews(String dbname) {
        sqL.delete(dbname, null, null);
    }

    /**
     * 查找并返回
     *
     * @param dbname 需要查找的表名
     * @return
     */
    public List<News> getnews(String dbname) {
        List<News> nhiulist = new ArrayList<News>();
        News news = null;
        Cursor cursor = null;
        int num = 0;
        cursor = sqL.rawQuery("select * from " + dbname, null);
        while (cursor.moveToNext()) {
            String imgurl = cursor.getString(cursor.getColumnIndex("imgurl"));
            String newsname = cursor.getString(cursor.getColumnIndex("newsname"));
            String newsurl = cursor.getString(cursor.getColumnIndex("newsurl"));
            String newsdate = cursor.getString(cursor.getColumnIndex("newsdate"));
            String newstype = cursor.getString(cursor.getColumnIndex("newstype"));
            news = new News(imgurl, null, newsname, newsurl, newsdate, newstype);
            nhiulist.add(news);
        }
        return nhiulist;
    }


    /**
     * 查找并返回
     *
     * @param dbname 需要查找的表名
     * @return
     */
    public List<News> getdimnews(String dbname, String name) {
        String nname = name;
        List<News> nhiulist = new ArrayList<News>();
        News news = null;
        Cursor cursor = null;
        int num = 0;
        cursor = sqL.rawQuery("select * from " + dbname, null);
        while (cursor.moveToNext()) {
            String newsname = cursor.getString(cursor.getColumnIndex("newsname"));
            if (newsname.contains(nname)) {
                String imgurl = cursor.getString(cursor.getColumnIndex("imgurl"));
                String newsurl = cursor.getString(cursor.getColumnIndex("newsurl"));
                String newsdate = cursor.getString(cursor.getColumnIndex("newsdate"));
                String newstype = cursor.getString(cursor.getColumnIndex("newstype"));
                news = new News(imgurl, null, newsname, newsurl, newsdate, newstype);
                nhiulist.add(news);
            }
        }
        return nhiulist;
    }

    /**
     * 获取标签状态
     *
     * @return
     */
    public List<String> gettab() {
        List<String> nhiulist = new ArrayList<String>();
        News news = null;
        Cursor cursor = null;
        int num = 0;
        cursor = sqL.rawQuery("select * from tab", null);
        while (cursor.moveToNext()) {
            String tabname = cursor.getString(cursor.getColumnIndex("tabname"));
            nhiulist.add(tabname);
        }
        return nhiulist;
    }

    /**
     * 删除所有标签状态
     */
    public void delecttab() {
        sqL.delete("tab", null, null);
    }
}
