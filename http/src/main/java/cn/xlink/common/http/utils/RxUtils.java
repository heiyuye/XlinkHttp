package cn.xlink.common.http.utils;


import rx.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Rx工具类
 *
 * @author sswukang on 2016/12/6 15:16
 * @version 1.0
 */
public class RxUtils {

    public static <T> Observable.Transformer<T, T> ioChangeObservable() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    public static <T> ObservableTransformer<T, T> ioChangeObservable() {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//    public static <T> FlowableTransformer<T, T> ioChangeFlowable() {
//        return new FlowableTransformer<T, T>() {
//            @Override
//            public Publisher<T> apply(Flowable<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }
//
//    public static <T> MaybeTransformer<T, T> ioChangeMaybe() {
//        return new MaybeTransformer<T, T>() {
//            @Override
//            public MaybeSource<T> apply(Maybe<T> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }
//        };
//    }

}
