package xlinkhttp.xlink.cn.xlinkhttp.viewmodel;

import android.databinding.ObservableField;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces.MainNavigator;

/**
 * Created by liucr on 2017/3/8.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {

    public ObservableField<String> text = new ObservableField<>();

    public RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
            refreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.finishLoadmore();
                }
            },2000);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            refreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.finishRefreshing();
                }
            },2000);
        }
    };

    @Override
    public void initViewModel() {
        text.set("12345");
    }

    @Override
    public void cancel() {

    }
}
