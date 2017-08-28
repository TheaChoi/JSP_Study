package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAO extends MemDAO {
	
	PreparedStatement pstm;
	ResultSet rs;
	
	public boolean login(String id, String pw) throws SQLException {
		
		Connection con = DBCP.getConnection();
		String sql = "select * from admin where id=? and pw=sha1(?)";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, id);
		pstm.setString(2, pw);
		rs = pstm.executeQuery();
		
		boolean ret;
		
		if(rs.next()==true){
			ret = true;
		}else{
			ret = false;
		}
		rs.close();
		pstm.close();
		con.close();
		
		return ret;
		
	}
}
