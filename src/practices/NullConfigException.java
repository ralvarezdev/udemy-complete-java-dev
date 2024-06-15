package practices;

public class NullConfigException extends Exception {
	public NullConfigException(String errorMessage) {
		super(errorMessage);
	}

	public NullConfigException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}