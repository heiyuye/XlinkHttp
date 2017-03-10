package xlinkhttp.xlink.cn.xlinkhttp.view.refreshrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

/**
 * Created by liucr on 2017/3/10.
 */

public class RefreshRecyclerView extends FrameLayout {

    //位于RecyclerView底层的emptyview，无法触发点击事件，RecyclerView依然可以下拉刷新
    private FrameLayout emptyLayout;
    //下拉刷新、上拉加载布局
    private TwinklingRefreshLayout refreshLayout;
    //RecyclerView
    private RecyclerView recyclerView;
    //位于RecyclerView上层，无法触发点击事件，RecyclerView依然可以下拉刷新
    private FrameLayout errorLayout;

    public RefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {

        emptyLayout = new FrameLayout(getContext());
        emptyLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(emptyLayout);

        refreshLayout = new TwinklingRefreshLayout(getContext());
        refreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
        refreshLayout.addView(recyclerView);
        addView(refreshLayout);

        errorLayout = new FrameLayout(getContext());
        errorLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(errorLayout);
        refreshLayout.setAutoLoadMore(true);
        refreshLayout.setEnableOverScroll(true);
    }

    public void startRefresh() {
        refreshLayout.startRefresh();
    }

    public void startLoadMore() {
        refreshLayout.startLoadMore();
    }

    public void setOnRefreshListener(RefreshListenerAdapter onRefreshListener) {
        refreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 设置没有数据时显示的View
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        if (emptyLayout.getChildCount() != 0) {
            emptyLayout.removeAllViews();
        }
        if (emptyView != null) {
            emptyLayout.addView(emptyView);
        }
    }

    /**
     * 设置发生异常时显示的View
     *
     * @param errorView
     */
    public void setErrorView(View errorView) {
        if (errorLayout.getChildCount() != 0) {
            errorLayout.removeAllViews();
        }
        if (errorView != null) {
            errorLayout.addView(errorView);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public TwinklingRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }
}
