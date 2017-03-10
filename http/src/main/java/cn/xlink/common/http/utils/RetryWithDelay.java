package cn.xlink.common.http.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;


/**
 * 延时重试
 *
 * @author sswukang on 2016/12/6 11:46
 * @version 1.0
 */
public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>>{

    private int maxRetries;
    private int retryDelayMillis;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelayMillis) {
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts.flatMap(new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                beforeRetry(throwable);

                if (maxRetries <= 0 || ++retryCount < maxRetries) {
                    // When this Observable calls onNext, the original
                    // Observable will be retried (i.e. re-subscribed).
                    return Observable.timer(retryDelayMillis,
                            TimeUnit.MILLISECONDS);
                }

                // Max retries hit. Just pass the error along.
                return Observable.error(throwable);
            }
        });
    }

    protected void beforeRetry(Throwable e) {
        Log.e("RetryWithDelay", "maxRetries: " + maxRetries + " retryCount: " + retryCount + "...");
    }

    public void stopRetry() {
        this.retryCount = 1;
        this.maxRetries = 1;
    }

//    public static class Observe implements Function<Observable<? extends Throwable>, ObservableSource<?>> {
//        private int maxRetries;
//        private int retryDelayMillis;
//        private int retryCount;
//
//        public Observe(int maxRetries, int retryDelayMillis) {
//            this.maxRetries = maxRetries;
//            this.retryDelayMillis = retryDelayMillis;
//            this.retryCount = 0;
//        }
//
//        @Override
//        public ObservableSource<?> apply(Observable<? extends Throwable> attempts) {
//            return attempts.flatMap(new Function<Throwable, ObservableSource<?>>() {
//                @Override
//                public ObservableSource<?> apply(Throwable throwable) {
//                    beforeRetry(throwable);
//
//                    if (maxRetries <= 0 || ++retryCount < maxRetries) {
//                        // When this Observable calls onNext, the original
//                        // Observable will be retried (i.e. re-subscribed).
//                        return Observable.timer(retryDelayMillis,
//                                TimeUnit.MILLISECONDS);
//                    }
//
//                    // Max retries hit. Just pass the error along.
//                    return Observable.error(throwable);
//                }
//            });
//        }
//
//        public void beforeRetry(Throwable e) {
//            Log.e("RetryWithDelay", "maxRetries: " + maxRetries + " retryCount: " + retryCount + "...");
//        }
//
//        public void stopRetry() {
//            this.retryCount = 1;
//            this.maxRetries = 1;
//        }
//    }
//
//    public static class Flow implements Function<Flowable<? extends Throwable>, Publisher<?>> {
//        private int maxRetries;
//        private int retryDelayMillis;
//        private int retryCount;
//
//        public Flow(int maxRetries, int retryDelayMillis) {
//            this.maxRetries = maxRetries;
//            this.retryDelayMillis = retryDelayMillis;
//            this.retryCount = 0;
//        }
//
//        @Override
//        public Publisher<?> apply(Flowable<? extends Throwable> attempts) {
//            return attempts.flatMap(new Function<Throwable, Publisher<?>>() {
//                @Override
//                public Publisher<?> apply(Throwable throwable) {
//                    beforeRetry(throwable);
//
//                    if (maxRetries <= 0 || ++retryCount < maxRetries) {
//                        // When this Observable calls onNext, the original
//                        // Observable will be retried (i.e. re-subscribed).
//                        return Flowable.timer(retryDelayMillis,
//                                TimeUnit.MILLISECONDS);
//                    }
//
//                    // Max retries hit. Just pass the error along.
//                    return Flowable.error(throwable);
//                }
//            });
//        }
//
//        public void beforeRetry(Throwable e) {
//            Log.e("RetryWithDelay", "maxRetries: " + maxRetries + " retryCount: " + retryCount + "...");
//        }
//
//        public void stopRetry() {
//            this.retryCount = 1;
//            this.maxRetries = 1;
//        }
//    }
}
