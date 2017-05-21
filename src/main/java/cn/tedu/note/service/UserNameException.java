package cn.tedu.note.service;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public class UserNameException extends RuntimeException {
    public UserNameException() {
        super();
    }

    public UserNameException(String message) {
        super(message);
    }

    public UserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameException(Throwable cause) {
        super(cause);
    }

    protected UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
