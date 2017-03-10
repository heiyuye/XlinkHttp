package xlinkhttp.xlink.cn.xlinkhttp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import xlinkhttp.xlink.cn.xlinkhttp.R;
import xlinkhttp.xlink.cn.xlinkhttp.databinding.ActivityLoginBinding;
import xlinkhttp.xlink.cn.xlinkhttp.viewmodel.LoginViewModel;

/**
 * Created by liucr on 2017/3/6.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDataBinding().setLoginViewModel(getViewModel());
    }

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initNavigator() {
        super.initNavigator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
