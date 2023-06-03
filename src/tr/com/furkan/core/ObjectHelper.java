package tr.com.furkan.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ObjectHelper {
	private String username="root";
	private String password="";
	private String url="jdbc:mysql://localhost:3306/veritabani";
	private static String driver="com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected Connection getConnection() {
		
		Connection connection=null;
		
		try {
			connection=DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	

}
