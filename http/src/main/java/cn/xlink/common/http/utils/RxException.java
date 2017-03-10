package cn.xlink.common.http.utils;

/**
 * 方便onError()回调判断
 *
 * @author sswukang on 2016/9/6 17:07
 * @version 1.0
 */

public class RxException extends Exception {
    private static final long serialVersionUID = -7134897190745733339L;

    /**
     * Constructs a new {@code RxException} that includes the current stack trace.
     */
    public RxException() {
    }

    /**
     * Constructs a new {@code RxException} with the current stack trace
     * and the specified detail message.
     *
     * @param detailMessage the detail message for this exception.
     */
    public RxException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code RxException} with the current stack trace,
     * the specified detail message and the specified cause.
     *
     * @param detailMessage the detail message for this exception.
     * @param throwable     the cause of this exception.
     */
    public RxException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * Constructs a new {@code RxException} with the current stack trace
     * and the specified cause.
     *
     * @param throwable the cause of this exception.
     */
    public RxException(Throwable throwable) {
        super(throwable);
    }
}
