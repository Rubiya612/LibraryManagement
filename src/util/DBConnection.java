package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
	private static final String USER = "COMP214_M25_ers_20"; 
	private static final String PASSWORD = "password";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException("Oracle JDBC Driver not found.");
		}
		
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}
}
