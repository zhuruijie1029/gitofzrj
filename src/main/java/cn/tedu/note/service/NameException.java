package cn.tedu.note.service;

public class NameException extends RuntimeException {
	private static final long serialVersionUID = 3051757784630921393L;

	public NameException() {
	}

	public NameException(String message) {
		super(message);
	}

	public NameException(Throwable cause) {
		super(cause);
	}

	public NameException(String message, Throwable cause) {
		super(message, cause);
	}

	public NameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
