package cn.tedu.note.service;

public class NotebookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7933689213724754594L;

	public NotebookNotFoundException() {
	}

	public NotebookNotFoundException(String message) {
		super(message);
	}

	public NotebookNotFoundException(Throwable cause) {
		super(cause);
	}

	public NotebookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotebookNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
