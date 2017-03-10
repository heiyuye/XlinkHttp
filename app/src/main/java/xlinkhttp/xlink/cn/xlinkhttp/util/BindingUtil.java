package xlinkhttp.xlink.cn.xlinkhttp.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.marshalchen.ultimaterecyclerview.SwipeableUltimateRecyclerview;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import xlinkhttp.xlink.cn.xlinkhttp.view.refreshrecyclerview.RefreshRecyclerView;

/**
 * Created by liucr on 2017/3/7.
 */

public class BindingUtil {

    /**
     * EditText 明文密文切换
     *
     * @param editText
     * @param enbalePassword
     */
    @BindingAdapter({"password_enbale"})
    public static void passwordInputType(EditText editText, boolean enbalePassword) {
        if (enbalePassword) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }

    /**
     * ImageView 加载网络图片
     *
     * @param view
     * @param url
     */
    @BindingAdapter({"image_src"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url)
                .centerCrop()
                .into(view);
    }

    /**
     * ImageView 加载网络图片
     *
     * @param view
     * @param id
     */
    @BindingAdapter({"image_src"})
    public static void loadImage(ImageView view, int id) {
        Glide.with(view.getContext()).load(id)
                .centerCrop()
                .into(view);
    }

    /**
     * 数据绑定方式执行
     *
     * @param view
     * @param manager
     */
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    /**
     * 数据绑定方式执行
     *
     * @param view
     * @param adapter
     */
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    /**
     * 数据绑定方式执行
     *
     * @param view
     * @param manager
     */
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RefreshRecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    /**
     * 数据绑定方式执行
     *
     * @param view
     * @param adapter
     */
    @BindingAdapter("adapter")
    public static void setAdapter(RefreshRecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    /**
     * 设置下拉刷新上拉加载监听
     *
     * @param view
     * @param refreshListenerAdapter
     */
    @BindingAdapter("refresh_listener")
    public static void setRefreshListenerAdapter(RefreshRecyclerView view, RefreshListenerAdapter refreshListenerAdapter) {
        view.setOnRefreshListener(refreshListenerAdapter);
    }

}
