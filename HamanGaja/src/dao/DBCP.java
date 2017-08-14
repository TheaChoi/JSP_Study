package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*이전:
 * Connection con = DriverManager.getConnection(url, uwer, pw);
 * 
 */

public class DBCP {
	
	public static Connection getConnection(){
		
		//1.context를 초기화 - context.xml 의 내용을 사용하기 위한 객체
		//2. 데이터 소스 - (pool)을 가져옴
		//3.데이터 소스에서 커넥션 전체를 꺼내옴
		Context context;
		DataSource dataSource;
		Connection con = null;
		
		try{
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/MyConn");
			con = dataSource.getConnection();
		}catch(NamingException e){
			System.out.println("네이밍 에러: "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("mysql 에러: "+e.getMessage());
		}
		return con;
	}

}
