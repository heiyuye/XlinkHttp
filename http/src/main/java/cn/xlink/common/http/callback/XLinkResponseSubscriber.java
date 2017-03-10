package cn.xlink.common.http.callback;


import java.io.IOException;

import cn.xlink.common.http.XLinkErrorCode;
import cn.xlink.common.http.utils.RxException;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * http反馈 Rx格式预处理
 *
 * @author sswukang on 2016/12/6 17:00
 * @version 1.0
 */
public abstract class XLinkResponseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            XLinkErrorCode.ErrorWrapper.Error error = XLinkErrorCode.parseError(exception.response());
            if (error == null) {
                error = new XLinkErrorCode.ErrorWrapper.Error();
                error.msg = exception.message();
                error.code = exception.code();
            }
            switch (error.code) {
                case XLinkErrorCode.NETWORK_UNAVAILABLE:
                case XLinkErrorCode.SERVICE_UNAVAILABLE:
                    onHttpError(new IOException(error.toString()));
                    break;
                default:
                    onApiError(error);
                    break;
            }
        } else if (e instanceof IOException) {
            onHttpError((IOException) e);
        } else if (e instanceof RxException) {
            onRxError((RxException) e);
        } else {
            onBreakdownError(e);
        }
    }

    @Override
    public final void onCompleted() {
    }

    public abstract void onBreakdownError(Throwable e);

    public abstract void onRxError(RxException e);

    public abstract void onHttpError(IOException e);

    public abstract void onApiError(XLinkErrorCode.ErrorWrapper.Error error);
}
