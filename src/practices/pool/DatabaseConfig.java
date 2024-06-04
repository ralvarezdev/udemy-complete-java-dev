package practices.pool;

public record DatabaseConfig(String host, String port, String database, String user, String password) {
	public String url() {
		return "jdbc:postgresql://" + host + ":" + port + "/" + database;
	}
}
