package cn.xlink.retrofithttp.callback;

import java.io.IOException;

import cn.xlink.retrofithttp.entity.HttpError;
import cn.xlink.retrofithttp.utils.JacksonUtil;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by liucr on 2017/3/6.
 */

public abstract class XlinkHttpSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        HttpError httpError = null;
        if (e instanceof HttpException) {
            try {
                httpError = JacksonUtil.toEntity(((HttpException) e).response().errorBody().string(), HttpError.class);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (httpError == null) {
            onHttpError(null, e);
        } else {
            onHttpError(httpError, null);
        }
    }

    @Override
    public void onNext(T t) {

    }

    public abstract void onHttpError(HttpError httpError, Throwable e);

}
