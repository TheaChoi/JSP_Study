package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemDTOIn;
import dto.ModDTOIn;
import dto.infoDTOOut;

//member ���̺��� �����ϴ� DAO
public class MemDAO {
	
	PreparedStatement pstm;
	int ret ;
	ResultSet rs;
	
	public boolean reg(MemDTOIn dto) throws SQLException{
		
		System.out.print("reg����");
		//1.Ŀ�ؼ� ��ü ��������
		Connection con = DBCP.getConnection();
		//2.SQL ���� �ۼ�
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
	
	public boolean login(String id, String pw) throws SQLException {
		
		Connection con = DBCP.getConnection();
		String sql = "select * from member where id=? and pw=sha1(?)";
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
	
	public boolean pwCheck(String pw) throws SQLException{
		
		Connection con = DBCP.getConnection();
		String sql = "select * from member where pw=sha1(?)";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, pw);
		rs = pstm.executeQuery();
		if(rs.next()==true){
			rs.close();
			pstm.close();
			con.close();
			return true;
		}else{
			rs.close();
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public boolean del(String id) throws SQLException{
		
		Connection con = DBCP.getConnection();
		String sql = "delete from member where id=?";
		pstm = con.prepareStatement(sql);
		pstm.setString(1, id);
		ret = pstm.executeUpdate();
		if(ret==1){
			return true;
		}else{
			return false;
		}
	}

	public infoDTOOut info(String id) throws SQLException
	   {
	      Connection con = DBCP.getConnection();
	      String sql="select * from member where id=?";
	      PreparedStatement pstm = con.prepareStatement(sql);
	      pstm.setString(1, id);
	      rs = pstm.executeQuery();
	      
	      if(rs.next() == true)
	      {
	    	  String phone = rs.getString("phone");
	    	  String email= rs.getString("email");
	    	  
	    	  infoDTOOut dto = new infoDTOOut(phone, email);
	    	  
	    	 
	         rs.close();
	         pstm.close();
	         con.close();
	         return dto;
	      }else
	      {
	         rs.close();
	         pstm.close();
	         con.close();
	         return null;
	      }
	   }
	
	public boolean mod(ModDTOIn dto) throws SQLException{
		
		Connection con = DBCP.getConnection();
		String sql = "update member set pw=sha1(?), phone=?, email=? where id=?";
		pstm = con.prepareStatement(sql); //�̰� ������ ����
		pstm.setString(1, dto.getPw());
		pstm.setString(2, dto.getPhone());
		pstm.setString(3, dto.getEmail());
		pstm.setString(4, dto.getId());
		ret = pstm.executeUpdate();
		if(ret==1){
			pstm.close();
	         con.close();
	         System.out.println("dao����");
	         return true;
		}else{
			pstm.close();
	         con.close();
	         System.out.println("dao����");
	         return false;
		}
	}
	
}
