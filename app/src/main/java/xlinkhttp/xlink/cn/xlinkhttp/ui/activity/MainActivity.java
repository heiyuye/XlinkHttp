package xlinkhttp.xlink.cn.xlinkhttp.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xlink.retrofithttp.entity.daoentity.User;
import xlinkhttp.xlink.cn.xlinkhttp.R;
import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces.MainNavigator;
import xlinkhttp.xlink.cn.xlinkhttp.adapter.multi.MultiBindAdapter;
import xlinkhttp.xlink.cn.xlinkhttp.databinding.ActivityMainBinding;
import xlinkhttp.xlink.cn.xlinkhttp.databinding.ItemUserBinding;
import xlinkhttp.xlink.cn.xlinkhttp.view.refreshrecyclerview.RefreshRecyclerView;
import xlinkhttp.xlink.cn.xlinkhttp.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {

    @BindView(R.id.refresh_recyclerview)
    RefreshRecyclerView refreshRecyclerView;

    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        getDataBinding().setMainViewModel(getViewModel());
        userList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setNickname(new Random().nextInt(900000) + "");
            userList.add(user);
        }
        initRecycView();

        refreshRecyclerView.startRefresh();
    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initNavigator() {
        getViewModel().setNavigator(this);
    }

    private void initRecycView() {
        MultiBindAdapter<User> adapter = new MultiBindAdapter<User>(userList) {
            @Override
            public int getItemLayoutId(User user, int position) {
                return R.layout.item_user;
            }

            @Override
            public void convert(User user, ViewDataBinding binding, @LayoutRes int layoutId) {
                ItemUserBinding itemUserBinding = getItemBinding(binding);
                itemUserBinding.setUser(user);
            }

            @Override
            public void onItemClick(View itemView, User user, @LayoutRes int layoutId) {
                super.onItemClick(itemView, user, layoutId);

            }
        };

        getDataBinding().setLayoutManager(new LinearLayoutManager(this));
        getDataBinding().setAdapter(adapter);
    }

}
