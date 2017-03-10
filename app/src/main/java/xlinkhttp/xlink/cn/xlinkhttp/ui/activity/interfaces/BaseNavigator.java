package xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces;

import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by liucr on 2017/3/7.
 */

public interface BaseNavigator {

    void showLoading();

    void showProgressDialog(String title, String message);

    void hidLoading();

    ProgressDialog getProgressDialog();

    void openActivity(Class<?> paramClass);

    void openActivity(Class<?> paramClass, Bundle paramBundle);

    void finish();
}
