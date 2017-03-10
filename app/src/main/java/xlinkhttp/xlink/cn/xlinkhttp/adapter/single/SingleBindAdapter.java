package xlinkhttp.xlink.cn.xlinkhttp.adapter.single;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

import xlinkhttp.xlink.cn.xlinkhttp.adapter.base.BaseBindAdapter;
import xlinkhttp.xlink.cn.xlinkhttp.adapter.base.BaseBindViewHolder;


/**
 * single item Adapter (DataBinding模式)
 *
 * @param <T> 数据类型
 * @param <B> 内容布局绑定类
 * @author sswukang on 2017/2/23 18:17
 * @version 1.0
 */
public abstract class SingleBindAdapter<T, B extends ViewDataBinding>
        extends BaseBindAdapter<T, B, BaseBindViewHolder<B>> {
    /**
     * @param layoutId adapter需要的布局资源id
     * @param data     数据
     */
    protected SingleBindAdapter(@LayoutRes int layoutId, List<T> data) {
        super(layoutId, data);
    }

    @Override
    public final void convert(T t, B binding, BaseBindViewHolder<B> holder) {
        convert(t, binding);
    }

    @Override
    public final void onItemClick(View itemView, int position, @LayoutRes int layoutId) {
        onItemClick(itemView, getItem(position));
    }

    @Override
    public final boolean onItemLongClick(View itemView, int position, @LayoutRes int layoutId) {
        return onItemLongClick(itemView, getItem(position));
    }

    /**
     * 实现该抽象方法，完成数据的绑定。
     *
     * @param t       每个 position 对应的封装
     * @param binding {@link B}
     */
    public abstract void convert(T t, B binding);

    /**
     * item的单击事件
     *
     * @param itemView 点击的item {@link BaseBindViewHolder<B>#itemView}
     * @param t        每个 position 对应的封装
     */
    public void onItemClick(View itemView, T t) {
        // do something...
    }

    /**
     * item的长按事件
     *
     * @param itemView 点击的item {@link BaseBindViewHolder<B>#itemView}
     * @param t        每个 position 对应的封装
     * @return 长按事件是否被消费
     */
    public boolean onItemLongClick(View itemView, T t) {
        return false;
    }
}
