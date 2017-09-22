package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ReplyDTOIn;
import dto.ReplyDTOOut;

//´ñ±Û°ü¸® DAO
public class ReplyDAO {

	
	public boolean write(ReplyDTOIn dto) throws SQLException{
		
		int link = dto.getLink();   //´ñ±ÛÀÌ ´Þ¸®´Â ¿øº» ±Û ¹øÈ£
		String content = dto.getContent();  //´ñ±Û ³»¿ë
		String id = dto.getId();            //´ñ±Û ÀÛ¼ºÀÚ ¾ÆÀÌµð
		
		Connection con = DBCP.getConnection();
		
		String sql ="insert into reply set link=?, content=?, id=?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setInt(1, link);
		pstm.setString(2, content);
		pstm.setString(3, id);
		
		int ret = pstm.executeUpdate();
		
		if(ret != 0){
			pstm.close();
			con.close();
			return true;
		}else{
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public ArrayList<ReplyDTOOut> read(int link) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="select * from reply where link=? order by num desc";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setInt(1, link);
		
		ArrayList<ReplyDTOOut> list = new ArrayList<ReplyDTOOut>();
		
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()==true){
			
			int num = rs.getInt("num");
			String id = rs.getString("id");
			String content = rs.getString("content");
			String reg_date = rs.getString("reg_date");
			ReplyDTOOut dto = new ReplyDTOOut(num, link,content,id,reg_date);
			list.add(dto);
		}
		rs.close();
		pstm.close();
		con.close();
		
		return list;
	}
	
	public boolean del(int num) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="delete from reply where num=?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setInt(1, num);
		
		int ret = pstm.executeUpdate();
		
		if(ret != 0){
			pstm.close();
			con.close();
			return true;
		}else{
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public boolean mod(int num, String content) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="update reply set content=? where num=?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		
		pstm.setString(1, content);
		pstm.setInt(2, num);
		
		int ret = pstm.executeUpdate();
		
		if(ret != 0){
			pstm.close();
			con.close();
			return true;
		}else{
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public boolean nice(int num, String type, String id) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="update board set nice=nice+1 where num=?";
		
		String sql1 = "insert into nice set id=?, type=?, link=?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
		pstm.setInt(1, num);
		
		PreparedStatement pstm1 = con.prepareStatement(sql1);
		pstm1.setString(1, id);
		pstm1.setString(2, type);
		pstm1.setInt(3, num);
		
		int ret = pstm.executeUpdate();
		int ret1 = pstm1.executeUpdate();
		
		if(ret != 0){
			pstm.close();
			con.close();
			return true;
		}else{
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public boolean niceDel(int num) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="update board set nice=nice-1 where num=?";
		PreparedStatement pstm = con.prepareStatement(sql);	
		pstm.setInt(1, num);
		
		String sql1 = "delete from nice where link=?";
		PreparedStatement pstm1 = con.prepareStatement(sql1);
		pstm1.setInt(1, num);
		
		int ret = pstm.executeUpdate();		
		int ret1 = pstm1.executeUpdate();

		
		if(ret != 0 && ret1 !=0){
			pstm.close();
			con.close();
			return true;
		}else{
			pstm.close();
			con.close();
			return false;
		}
	}
	
	public void click(int num) throws SQLException{
		
		Connection con = DBCP.getConnection();
		
		String sql ="update board set count=count+1 where num=?";
		
		PreparedStatement pstm = con.prepareStatement(sql);
	
		pstm.setInt(1, num);
		
		pstm.executeUpdate();
	
	    pstm.close();
		con.close();
		
	}
	
}
