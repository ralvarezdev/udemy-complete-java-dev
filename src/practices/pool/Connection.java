package practices.pool;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

interface Connection {
	public boolean connect(DatabaseConfig config, boolean autoCommit);

	public void disconnect();

	public boolean commit();

	public boolean rollback();

	public boolean isClosed();

	public boolean isValid();

	public void createPreparedStatement(String sql);

	public void closePreparedStatement();

	public void setStringParameter(int paramCounter, String param) throws NullPointerException, SQLException;

	public void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException;

	public void setFloatParameter(int paramCounter, float param) throws NullPointerException, SQLException;

	public void setDoubleParameter(int paramCounter, double param) throws NullPointerException, SQLException;

	public void setBigDecimalParameter(int paramCounter, BigDecimal param) throws NullPointerException, SQLException;

	public void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException;

	public Integer executeUpdate() throws NullPointerException;

	public <T> List<T> executeQuery(ResultSetFunction<T> func) throws NullPointerException;
}
