package xlinkhttp.xlink.cn.xlinkhttp.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import java.util.List;

import cn.xlink.retrofithttp.callback.XlinkHttpSubscriber;
import cn.xlink.retrofithttp.database.DatabaseUtil;
import cn.xlink.retrofithttp.entity.HttpError;
import cn.xlink.retrofithttp.entity.UserAuth;
import cn.xlink.retrofithttp.entity.daoentity.User;
import cn.xlink.retrofithttp.xlinkretrofithttp.XlinkApiManager;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.MainActivity;
import xlinkhttp.xlink.cn.xlinkhttp.ui.activity.interfaces.BaseNavigator;

/**
 * Created by liucr on 2017/3/6.
 */

public class LoginViewModel extends BaseViewModel<BaseNavigator> {

    public final ObservableField<String> phoneNumber = new ObservableField<>();
    public final ObservableField<String> passsword = new ObservableField<>();
    public final ObservableField<String> userMsg = new ObservableField<>();
    public final ObservableField<String> mAvatarUrl = new ObservableField<>();

    private Subscription loginSubscription;

    @Override
    public void initViewModel() {
        List<User> userList = DatabaseUtil.getInstance().getUserDao().loadAll();
        User user = null;
        if (userList.size() != 0) {
            user = userList.get(0);
        } else {
            user = new User();
            user.setPhone("13824416427");
        }

        phoneNumber.set(user.getPhone());
        passsword.set(user.getPassword());
        mAvatarUrl.set(user.getAvatar());
    }

    public final void onClickLogin(View view) {
        navigator.showLoading();
        if (loginSubscription != null) {
            loginSubscription.unsubscribe();
        }

        loginSubscription = XlinkApiManager.getInstance().login(phoneNumber.get(), passsword.get())
                .flatMap(new Func1<UserAuth, Observable<User>>() {
                    @Override
                    public Observable<User> call(UserAuth userAuth) {
                        XlinkApiManager.getInstance().setAccessToken(userAuth.getAccess_token());
                        return XlinkApiManager.getInstance().getUserMsg(userAuth.getUser_id());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new XlinkHttpSubscriber<User>() {
                    @Override
                    public void onHttpError(HttpError httpError, Throwable e) {
                        if (httpError != null) {
                            userMsg.set(httpError.toString());
                        } else {
                            userMsg.set(e.toString());
                        }
                    }

                    @Override
                    public void onNext(User user) {
                        super.onNext(user);
                        user.setPassword(passsword.get());
                        DatabaseUtil.getInstance().getUserDao().insertOrReplace(user);
                        userMsg.set(user.toString());
                        mAvatarUrl.set(user.getAvatar());
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        navigator.hidLoading();
                        navigator.openActivity(MainActivity.class);
                        navigator.finish();
                    }
                });
    }

    @Override
    public void cancel() {
        if (loginSubscription != null) {
            loginSubscription.unsubscribe();
        }
    }
}
