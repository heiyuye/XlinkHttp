package xlinkhttp.xlink.cn.xlinkhttp.viewmodel;

import android.databinding.BaseObservable;

import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces.BaseNavigator;

/**
 * Created by liucr on 2017/3/7.
 */

public abstract class BaseViewModel<N extends BaseNavigator> extends BaseObservable {

    protected N navigator;

    public BaseViewModel(N navigator) {
        this.navigator = navigator;
    }

    public BaseViewModel() {
    }

    public abstract void initViewModel();

    public void setNavigator(N navigator) {
        this.navigator = navigator;
    }

    public abstract void cancel();

}
