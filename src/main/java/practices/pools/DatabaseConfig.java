package practices.pools;

public record DatabaseConfig(String host, String port, String database, String user, String password) {
    public String url(String driver) {
        return "jdbc:%s://%s:%s/%s".formatted(driver, host, port, database);
    }
}
