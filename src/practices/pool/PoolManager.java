package practices.pool;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface PoolManager {
	public boolean isValid();

	public void getConnection();

	public void putConnection();

	public void commit();

	public void rollback();

	public void createPreparedStatement(String sql);

	public void closePreparedStatement();

	public void setStringParameter(int paramCounter, String param) throws NullPointerException, SQLException;

	public void setIntParameter(int paramCounter, int param) throws NullPointerException, SQLException;

	public void setFloatParameter(int paramCounter, float param) throws NullPointerException, SQLException;

	public void setDoubleParameter(int paramCounter, double param) throws NullPointerException, SQLException;

	public void setBigDecimalParameter(int paramCounter, BigDecimal param) throws NullPointerException, SQLException;

	public void setLongParameter(int paramCounter, long param) throws NullPointerException, SQLException;

	public Integer executeUpdate();

	public <T> List<T> executeQuery(ResultSetFunction<T> func);
}
