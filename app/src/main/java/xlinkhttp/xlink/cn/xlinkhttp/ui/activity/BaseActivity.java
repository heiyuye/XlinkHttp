package xlinkhttp.xlink.cn.xlinkhttp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces.BaseNavigator;
import xlinkhttp.xlink.cn.xlinkhttp.viewmodel.BaseViewModel;

/**
 * Created by liucr on 2017/3/6.
 */

public abstract class BaseActivity<B extends ViewDataBinding, M extends BaseViewModel> extends AppCompatActivity implements BaseNavigator {

    private B mViewDataBinding;
    private M mViewModel;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("   loading...");
        mViewDataBinding = DataBindingUtil.setContentView(this, initLayout());

        // 得到ViewModel
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<M> bizClass = (Class) params[1]; // 泛型位置
        try {
            mViewModel = bizClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initNavigator();
        mViewModel.initViewModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getViewModel().cancel();
        getDataBinding().unbind();
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public abstract int initLayout();

    public void initNavigator() {
        mViewModel.setNavigator(this);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showProgressDialog(String title, String message) {
        progressDialog.setTitle(title);
        progressDialog.setTitle(message);
        progressDialog.show();
    }

    @Override
    public void hidLoading() {
        progressDialog.hide();
    }

    @Override
    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    @Override
    public void openActivity(Class<?> paramClass) {
        openActivity(paramClass, null);
    }

    @Override
    public void openActivity(Class<?> paramClass, Bundle paramBundle) {
        Intent localIntent = new Intent(this, paramClass);
        if (paramBundle != null)
            localIntent.putExtras(paramBundle);
        startActivity(localIntent);
    }

    public B getDataBinding() {
        return mViewDataBinding;
    }

    public M getViewModel() {
        return mViewModel;
    }
}
