package cn.tedu.note.service;

public class UserExistException extends RuntimeException {
	private static final long serialVersionUID = -762935833477893409L;

	public UserExistException() {
	}

	public UserExistException(String message) {
		super(message);
	}

	public UserExistException(Throwable cause) {
		super(cause);
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
