package cn.xlink.common.http.callback;

import cn.xlink.common.http.XLinkErrorCode;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * http反馈 普通格式预处理
 *
 * @author sswukang on 2016/8/25 17:00
 * @version 1.0
 */
public abstract class XLinkResponseCallback<T> implements Callback<T> {
    @Override
    public final void onResponse(Call<T> call, Response<T> response) {
        if (!response.isSuccessful()) {
            XLinkErrorCode.ErrorWrapper.Error error = XLinkErrorCode.parseError(response);
            if (error == null) {
                error = new XLinkErrorCode.ErrorWrapper.Error();
                error.msg = response.message();
                error.code = response.code();
            }
            onApiError(error);
        } else {
            onSuccess(response.body());
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        onHttpError(t);
    }

    public abstract void onHttpError(Throwable e);

    public abstract void onApiError(XLinkErrorCode.ErrorWrapper.Error error);

    public abstract void onSuccess(T t);
}
