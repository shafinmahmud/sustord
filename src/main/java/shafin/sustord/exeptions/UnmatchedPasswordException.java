package shafin.sustord.exeptions;

public class UnmatchedPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnmatchedPasswordException() {
	}

	public UnmatchedPasswordException(String message) {
		super(message);
	}

	public UnmatchedPasswordException(Throwable cause) {
		super(cause);
	}

	public UnmatchedPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnmatchedPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
