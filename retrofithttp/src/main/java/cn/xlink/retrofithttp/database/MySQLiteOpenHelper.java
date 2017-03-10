package cn.xlink.retrofithttp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xlink.greendao.gen.DaoMaster;
import com.xlink.greendao.gen.UserDao;

/**
 * Created by liucr on 2017/3/2.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, UserDao.class);
    }
}