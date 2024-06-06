package practices;

public class MissingResourceFileException extends Exception {
	public MissingResourceFileException(String errorMessage) {
		super(errorMessage);
	}

	public MissingResourceFileException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
