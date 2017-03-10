package xlinkhttp.xlink.cn.xlinkhttp;

import android.app.Application;

import cn.xlink.retrofithttp.database.DatabaseUtil;

/**
 * Created by liucr on 2017/3/1.
 */

public class XlinkHttpApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseUtil.init(this);
    }
}
