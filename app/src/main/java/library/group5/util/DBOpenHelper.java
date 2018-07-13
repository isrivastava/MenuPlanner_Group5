package library.group5.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Isha on 05-04-2018.
 */

public class DBOpenHelper  extends SQLiteOpenHelper {
    public DBOpenHelper(Context context, String path, int version){
        super(context, path, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}