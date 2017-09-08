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
      
      String sql = "select num, photo, title, content from board where type=? order by num desc limit 0,?"; //0개부터 ? 개수의 게시판을 표현 (최신순으로)
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
		int start = pageNo * pageSize;  //시작글의 위치
		int mode = page.getMode(); //-1검색어 미사용, 0이면 검색어 사용 리스트 만든다
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
			//검색어를 사용하는 sql 문장 작성
			String sql = "select num, title, reg_date, count, photo, content from board where type=? and title like ? order by num desc limit ?,?";  //이 안에 %쓰지 말고
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
			
			//각 글목록을 객체에 저장함
			UserDTOOut dto = new UserDTOOut(num, count, title, content, reg_date, photo, type);
			
			//글목록을 저장한 객체를 리스트배열에 추가함
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
		
		if(mode==-1){ //검색어를 이용하지 않는경우
			 String sql  = "select count(*) from board where type=?";
		      pstm = con.prepareStatement(sql);
		      pstm.setString(1, type);
		}else{ //검색어 사용함
			 String sql  = "select count(*) from board where type=? and title like ?";
			 String word = "%"+page.getWord()+"%";
		      pstm = con.prepareStatement(sql);
		      pstm.setString(1, type);
		      pstm.setString(2, word);
		}
	
	      rs = pstm.executeQuery();
	      
	      rs.next();
	      int count = rs.getInt("count(*)");
	      System.out.println(type+"게시판 글개수:"+count);
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