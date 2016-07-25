package feicui.newsapp02.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1099057173 on 2016/7/23.
 */
public class NewsDB extends SQLiteOpenHelper {
    public NewsDB(Context mC) {
        super(mC, "opennews.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table wxnew(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table huabian(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table guonei(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table health(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table keji(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table tiyu(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");

        db.execSQL("create table collect(id integer primary key autoincrement,imgurl text,newsname text" +
                ",newsurl text,newsdate text,newstype text)");
        db.execSQL("create table tab(id integer primary key autoincrement,tabname text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}