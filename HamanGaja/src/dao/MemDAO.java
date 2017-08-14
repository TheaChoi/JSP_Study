package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemDTOIn;

//member 테이블을 관리하는 DAO
public class MemDAO {
	
	PreparedStatement pstm;
	int ret ;
	ResultSet rs;
	
	public boolean reg(MemDTOIn dto) throws SQLException{
		
		
		//1.커넥션 객체 꺼내오기
		Connection con = DBCP.getConnection();
		//2.SQL 문장 작성
		String sql = "insert into member set id=?, pw=sha1(?), phone=?, email=?,reg_date=now(), address=?";
		
		pstm = con.prepareStatement(sql);
		
		pstm.setString(1, dto.getId());
		pstm.setString(2, dto.getPw());
		pstm.setString(3, dto.getPhone());
		pstm.setString(4, dto.getEmail());
		pstm.setString(5, dto.getAddress());
		
		ret = pstm.executeUpdate();
		
		if(ret==1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean login(String id, String pw) throws SQLException{
		
		Connection con = DBCP.getConnection();		
		String sql = "select * from member where id=? and pw=sha1(?)";		
		pstm = con.prepareStatement(sql);
		pstm.setString(1, id);
		pstm.setString(2, pw);
		rs = pstm.executeQuery();
		
		if(rs.next()==true){
			return true;
		}else{
			return false;
		}
		
	}
	
	public boolean idCheck(String id) throws SQLException {
		
		Connection con = DBCP.getConnection();
		String sql = "select * from member where id=?";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, id);
		rs = pstm.executeQuery();
		if(rs.next()==true){
			return false;
		}else{
			return true;
		}
		
	}

	
}
