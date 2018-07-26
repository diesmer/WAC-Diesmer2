package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
	Connection conn = null;
	protected final Connection getConnection() {


		try {
			InitialContext ic = new InitialContext();
			DataSource datasource = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			
			conn = datasource.getConnection();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		return conn;
	}
	
	public boolean Update(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		return stmt.executeUpdate(query) == 1 ? true : false;
	}
}
