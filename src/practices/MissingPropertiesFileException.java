package practices;

public class MissingPropertiesFileException extends Exception {
	public MissingPropertiesFileException(String errorMessage) {
		super(errorMessage);
	}

	public MissingPropertiesFileException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
