package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMng {
	private static DBMng dbMng = null;
	private static Connection conn = null;
	
	private DBMng() {
		// 싱글턴 패턴이 적용된 클래스
	
	}
	/**
	 * DBMS 커넥션을 반환하는 매서드 
	 * @return java.sql.Connection
	 * @throws SQLException : DBMS에 문제가 생겼을 떄 발생
	 */
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3307/myapplicationdb?user=root&password=koreait");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} // end try
		} // end if
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				
				conn = null;
			} catch(SQLException e) {
				e.printStackTrace();
			} // end try
		} // end if
	}
		
}
		
		
	