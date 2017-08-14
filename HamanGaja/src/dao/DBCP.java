package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*����:
 * Connection con = DriverManager.getConnection(url, uwer, pw);
 * 
 */

public class DBCP {
	
	public static Connection getConnection(){
		
		//1.context�� �ʱ�ȭ - context.xml �� ������ ����ϱ� ���� ��ü
		//2. ������ �ҽ� - (pool)�� ������
		//3.������ �ҽ����� Ŀ�ؼ� ��ü�� ������
		Context context;
		DataSource dataSource;
		Connection con = null;
		
		try{
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/MyConn");
			con = dataSource.getConnection();
		}catch(NamingException e){
			System.out.println("���̹� ����: "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("mysql ����: "+e.getMessage());
		}
		return con;
	}

}
