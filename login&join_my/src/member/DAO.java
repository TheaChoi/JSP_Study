package member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	
	public int ret; //insert�� ���� �߰��� ������ ����
	public Statement stm;
	public ResultSet rs;
	
	public DAO() throws ClassNotFoundException , SQLException {
		
		Connection con;
		
		
		
		String url="jdbc:mysql://localhost:3306/java";
		String DBid = "root";
		String DBpw = "5456" ;
		
		//1.Class.forName ����̹� Ȯ��
		Class.forName("com.mysql.jdbc.Driver");
				
		//2. ����
		con = DriverManager.getConnection(url, DBid, DBpw);
				
		//3.Statement��ü �����
		stm = con.createStatement();
		
	}
	
	public boolean reg(String id, String pw, String name) throws ClassNotFoundException, SQLException {
		
		String sql = "insert into member set id='"+id+"', ";
			sql += "pw=md5('"+pw+"'), ";
			sql += "name='"+name+"', ";
			sql += "count=0, reg_date=now();";
			
			System.out.println("reg : sql = "+sql);
		
		
		
		//4.sql ���� �ۼ� --> ����
		ret = stm.executeUpdate(sql);  //executeQuery�� ǥ�� ������ ���, executeUpdate�� ǥ�� �ȳ����� ���
		
		
		//5.���� ��� Ȯ��
		System.out.println("reg:ret="+ret);
		
		if(ret==1) {
			return true;
		}else{
			return false;
		}
	}
	
	
	//ȸ������ �޼ҵ�
	public boolean login(String id, String pw) throws SQLException {
		
		//1.SQL ���ǹ��� �����
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
	
	//�α��� Ƚ���� ������Ű�� �޼ҵ�
	void inc(String id) throws SQLException {
		
		//1.�Է¹��� id�� �ش��ϴ� count ���� ������
		
		id = "'"+id+"'";
		String sqlR = "select count from member where id="+id+";";
		
		//2.sql������ ���� ��Ŵ
		
		rs = stm.executeQuery(sqlR);
		
		if(rs.next() == true) {
			
			int count = rs.getInt("count"); //���� ī��Ʈ ���� �о��
			
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
	
	public boolean idCheck(String id) throws SQLException {
		
		id = "'"+id+"'";
		String sql="select * from member where id="+id;
		
		rs = stm.executeQuery(sql);
		
		if(rs.next()==true){
			return false;
		}
		else{
			return true;
		}
	}

}
