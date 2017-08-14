package member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	int ret; //insert에 의해 추가된 라인의 개수
	Statement stm;
	ResultSet rs;
	
	public DAO() throws ClassNotFoundException , SQLException {
		
		Connection con;
		
		
		
		String url="jdbc:mysql://localhost:3306/java";
		String DBid = "root";
		String DBpw = "2fldhsjfk**" ;
		
		//1.Class.forName 드라이버 확인
		Class.forName("com.mysql.jdbc.Driver");
				
		//2. 접속
		con = DriverManager.getConnection(url, DBid, DBpw);
				
		//3.Statement객체 만들기
		stm = con.createStatement();
		
	}
	
	public boolean reg(String id, String pw, String name) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into member set id='"+id+"', ";
			sql += "pw=md5('"+pw+"'), ";
			sql += "name='"+name+"', ";
			sql += "count=0, reg_date=now();";
			
			System.out.println("reg : sql = "+sql);
		
		
		
		//4.sql 문장 작성 --> 실행
		ret = stm.executeUpdate(sql);  //executeQuery는 표가 나오는 경우, executeUpdate는 표가 안나오는 경우
		
		
		//5.실행 결과 확인
		System.out.println("reg:ret="+ret);
		
		if(ret==1) {
			return true;
		}else{
			return false;
		}
	}
	
	
	//회원가입 메소드
	public boolean login(String id, String pw) throws SQLException {
		
		//1.SQL 질의문을 만든다
		id = "'"+id+"'";
		pw = "md5('"+pw+"')";
		
		String sql = "select * from member ";
				sql += "where id="+id+" and pw="+pw;
				
		//System.out.println("login: sql="+sql);
				
		rs = stm.executeQuery(sql);
		
		
		if(rs.next() == true) {
			return true;
		}else{
			return false;
		}
	}
	
	//로그인 횟수를 증가시키는 메소드
	void inc(String id) throws SQLException {
		
		//1.입력받은 id에 해당하는 count 값을 가져옴
		
		id = "'"+id+"'";
		String sqlR = "select count from member where id="+id+";";
		
		//2.sql문장을 실행 시킴
		
		rs = stm.executeQuery(sqlR);
		
		if(rs.next() == true) {
			
			int count = rs.getInt("count"); //현재 카운트 값을 읽어옴
			
			count++;
			
			String sqlW = "update member set count="+count;
					sqlW += " where id="+id+";";
					
			stm.executeUpdate(sqlW);
		}
	}
	
	
	void del(String id) throws SQLException {
		 
		 String sql = "delete from member where id='"+id+"';";
		 
		 stm.executeUpdate(sql);
	 }
	
	InfoData getInfo(String id) throws SQLException {
		
		String sql = "select pw, name from member where id='"+id+"';";
		
		rs = stm.executeQuery(sql);
		
		if(rs.next()== true) {
			
			String pw =  rs.getString("pw");
			String name = rs.getString("name");
			
			
			InfoData data = new InfoData();
			
			data.name = name;
			data.pw = pw;
			
			return data;
			
			/****************
			
			System.out.println("pw :"+pw);
			System.out.println("name :"+name);
			****************/
			
		}
		
		return null;
		
	}
	
	boolean setInfo(String id, InfoData data) throws SQLException {
		
		id = "'"+id +"'";
		String pw = "md5('"+data.pw+"')";
		String name = "'"+data.name+"'";
		
		String sql = "update member set pw="+pw+", name="+name;
			sql += " where id="+id+";";
			
		ret = stm.executeUpdate(sql);
		
		if(ret==1) {
			
			return true;
		}else {
			return false;
		}
	}
	
	boolean chgPassword(String id, String pw1, String pw2) throws SQLException {
		
		id = "'"+id +"'";
		pw1 = "md5('"+pw1+"')";
		pw2 = "md5('"+pw2+"')";
		
		String sql = "update member set pw="+pw2;
				sql += " where id="+id+" and pw = "+pw1+";";
				
		ret = stm.executeUpdate(sql);
		
		if(ret==1){
			return true;
		}else{
			return false;
		}
		//System.out.println("chgPassword ret="+ret);
	}

}
