package book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	private static final String driverPath = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://127.0.0.1:3307/db2022_2_2?severTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String id = "root";
	private static final String pwd = "1234";
	private static Connection con;
	
	public static Connection getCon() {
		try {
//			JDBC Driver 클래스를 로딩
			Class.forName(driverPath);
			System.out.println("Driver가 정상적으로 로드되었습니다.");
//			설정된 url, id, pwd를 사용하여 db와 연결을 시도한 후 연결객체를 반환하는 메소드
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결이 정상적으로 되었습니다.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void rsTest() {
		try {
//			SQL문을 실행할 수 있는 문장객체 생성
			Statement stmt = con.createStatement();
			String sql = "select * from book";
//			SQL문 실행(select문을 실행할 때는 executeQuery()메소드를 사용하여 실행: ResultSet(결과행의 집합) 반환
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()) {
		    	System.out.println(rs.getString("name"));
		    }
		    rs.close();
		    stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		JDBCConnector dl = new JDBCConnector();
		dl.getCon();
		dl.rsTest();
	}

}
