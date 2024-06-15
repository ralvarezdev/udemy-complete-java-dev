package practices.pool;

public enum DatabasePoolProperties {
	INIT_CONNS("INIT_CONNS"), INCR_CONNS("INCR_CONNS"), MAX_CONNS("MAX_CONNS");

	private final String fieldName;

	private DatabasePoolProperties(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return this.fieldName;
	}
}
