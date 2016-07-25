package feicui.newsapp02.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1099057173 on 2016/7/24.
 */
public class Search extends SQLiteOpenHelper {
    public Search(Context context) {
        super(context, "mysearch.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table search(id integer PRIMARY KEY autoincrement,name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
