package cn.xlink.retrofithttp.entity;

/**
 * Created by liucr on 2017/3/6.
 */

public class HttpError {

    public HttpError() {
        error = new Error();
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public int getCode() {
        return error.getCode();
    }

    public String getMsg() {
        return error.getMsg();
    }

    public void setMsg(String msg) {
        error.setMsg(msg);
    }

    private Error error;

    public static class Error {
        private int code;
        private String msg;

        public void setCode(int code) {
            this.code = code;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HttpError{" +
                "error=" + error +
                '}';
    }
}
