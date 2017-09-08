package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDTOOut;
import dto.ListDTOOut;
import dto.ListMainDTOOut;
import dto.UserDTOOut;
import dto.pageDTOIn;

public class UserDAO 
{
   public ArrayList<ListMainDTOOut> listmain(String type,int cnt) throws SQLException
   {
	   ListMainDTOOut dto = null;
      Connection con = DBCP.getConnection();
      
      String sql = "select num, photo, title, content from board where type=? order by num desc limit 0,?"; //0������ ? ������ �Խ����� ǥ�� (�ֽż�����)
      PreparedStatement pstm = con.prepareStatement(sql);
      
      pstm.setString(1, type);
      pstm.setInt(2, cnt);
      
      ArrayList<ListMainDTOOut> list = new ArrayList<ListMainDTOOut>();
      
      ResultSet rs = pstm.executeQuery();
      
      while(rs.next()==true){
    	  int num = rs.getInt("num");
    	  String photo = rs.getString("photo");
    	  String title = rs.getString("title");
    	  String content = rs.getString("content");
    	  
    	  dto = new ListMainDTOOut(num, title, photo, content);
    	  
    	  list.add(dto);
//    	  System.out.println(num);
//    	  System.out.println(title);
//    	  System.out.println(content);
//    	  System.out.println(photo);
      }
	      rs.close();
	      pstm.close();
	      con.close();
	      
	      return list;
  
   }
   
   public ArrayList<UserDTOOut> list(pageDTOIn page) throws SQLException{
	   
	    int pageNo = page.getPageNo();
		int pageSize = page.getPageSize();
		int start = pageNo * pageSize;  //���۱��� ��ġ
		int mode = page.getMode(); //-1�˻��� �̻��, 0�̸� �˻��� ��� ����Ʈ �����
	 	String type = page.getType();
		
		Connection con = DBCP.getConnection();
		PreparedStatement pstm;
		ResultSet rs;
		
		ArrayList<UserDTOOut> list = new ArrayList<UserDTOOut>();
		
		if(mode!=0)
		{
			String sql = "select num, title, reg_date, count, photo from board where type=? order by num desc limit ?,?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1,type);
			pstm.setInt(2, start);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			
		}else{
			//�˻�� ����ϴ� sql ���� �ۼ�
			String sql = "select num, title, reg_date, count, photo, content from board where type=? and title like ? order by num desc limit ?,?";  //�� �ȿ� %���� ����
			String word = "%"+page.getWord()+"%";  
			pstm = con.prepareStatement(sql);
			pstm.setString(1,type);
			pstm.setString(2, word);
			pstm.setInt(3, start);
			pstm.setInt(4, pageSize);
			rs = pstm.executeQuery();
			
		}
		while(rs.next()==true){
			int num = rs.getInt("num");
			String title = rs.getString("title");
			String reg_date = rs.getString("reg_date");
			int count = rs.getInt("count");
			String photo = rs.getString("photo");
			String content = rs.getString("content");
			
			//�� �۸���� ��ü�� ������
			UserDTOOut dto = new UserDTOOut(num, count, title, content, reg_date, photo, type);
			
			//�۸���� ������ ��ü�� ����Ʈ�迭�� �߰���
			list.add(dto);
		}
		rs.close();
		pstm.close();
		con.close();
		return list;
	}
   
   public int countAll(String type, pageDTOIn page) throws SQLException{
		
		Connection con = DBCP.getConnection();
		PreparedStatement pstm;
		ResultSet rs;
		int mode = page.getMode();
		
		if(mode==-1){ //�˻�� �̿����� �ʴ°��
			 String sql  = "select count(*) from board where type=?";
		      pstm = con.prepareStatement(sql);
		      pstm.setString(1, type);
		}else{ //�˻��� �����
			 String sql  = "select count(*) from board where type=? and title like ?";
			 String word = "%"+page.getWord()+"%";
		      pstm = con.prepareStatement(sql);
		      pstm.setString(1, type);
		      pstm.setString(2, word);
		}
	
	      rs = pstm.executeQuery();
	      
	      rs.next();
	      int count = rs.getInt("count(*)");
	      System.out.println(type+"�Խ��� �۰���:"+count);
	      rs.close();
		pstm.close();
		con.close();
		return count;
		
	}
   
   public BoardDTOOut read(int num) throws SQLException
   {
	BoardDTOOut dto=null;
      Connection con = DBCP.getConnection();
      String sql = "select * from board where num=?";
      PreparedStatement pstm = con.prepareStatement(sql);
      pstm.setInt(1, num);
      
      ResultSet rs = pstm.executeQuery();
      
      if(rs.next()==true)
      {
         String type=rs.getString("type");
         String title = rs.getString("title");
         String content = rs.getString("content");
         String photo = rs.getString("photo");
         String map = rs.getString("map");
         int nice = rs.getInt("nice");
         int count = rs.getInt("count");
         String reg_date = rs.getString("reg_date");
         
         dto = new BoardDTOOut(num,nice, count, type, title, content, photo, map, reg_date);
        
      }
      rs.close();
      pstm.close();
      con.close();
      return dto;
      
   }
	
   
   
}