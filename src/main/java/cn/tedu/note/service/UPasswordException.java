package cn.tedu.note.service;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class UPasswordException extends RuntimeException{
    public UPasswordException() {
        super();
    }

    public UPasswordException(String message) {
        super(message);
    }

    public UPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UPasswordException(Throwable cause) {
        super(cause);
    }

    protected UPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
