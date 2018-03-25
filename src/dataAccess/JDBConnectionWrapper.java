package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBConnectionWrapper {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/theater?useSSL=false";

	private static final String USER = "root";
	private static final String PASS = "meade110696";
	private static final int TIMEOUT = 5;

	private Connection connection;

	public JDBConnectionWrapper(String schema) {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean testConnection() throws SQLException {
		return connection.isValid(TIMEOUT);
	}

	public Connection getConnection() {
		return connection;
	}

}
