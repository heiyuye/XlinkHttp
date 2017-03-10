package cn.xlink.retrofithttp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xlink.greendao.gen.DaoMaster;
import com.xlink.greendao.gen.DaoSession;
import com.xlink.greendao.gen.UserDao;

import java.util.List;

import cn.xlink.retrofithttp.entity.daoentity.User;
import rx.Observable;

/**
 * Created by liucr on 2017/3/1.
 */

public class DatabaseUtil {

    private static DatabaseUtil instance;

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public DatabaseUtil(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "xlinkhttp-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public static void init(Context context) {
        instance = new DatabaseUtil(context);
    }

    public static DatabaseUtil getInstance() {
        return instance;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public UserDao getUserDao() {
        return mDaoSession.getUserDao();
    }

    public Observable<List<User>> loadAllUser() {
        return getUserDao().rx().loadAll();
    }

}
