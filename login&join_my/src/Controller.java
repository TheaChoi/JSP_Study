import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.DAO;

@WebServlet(name = "Controller", urlPatterns = { "/Controller" })
public class Controller extends HttpServlet
{
   DAO dao; //모델 객체 저장 변수(참조변수)
   
   public Controller() throws ClassNotFoundException, SQLException
   {
      dao= new DAO(); //DAO 객체 생성 (생성자) 
   }
   
   
  
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
	   
	   //한글이 깨진다!
	   request.setCharacterEncoding("UTF-8");
	   response.setContentType("text/html; charset=utf-8");
	   
	   String cmd = request.getParameter("cmd");
	   System.out.println("cmd="+cmd);
	   
      //세션과 리퀘스트의 차이점을 알아두자!! 중요!!
      //세션 객체 가져오기
   //   HttpSession session=request.getSession();
      
      String id=request.getParameter("id");
      String pw=request.getParameter("pw");
      
      System.out.println("-------doget 메소드------");
      
//      System.out.println("id "+id);
//      System.out.println("pw "+pw);
      try 
      {
         //if(dao.login(id, pw)==true)
    	  if(cmd.equals("login")==true)
         {
    		  doLogin(request, response);
            //request 객체를 이용해서 값을 전달함
            //request.setAttribute("UserID", id);
            
            //session객체를 이용해서 값을 전달함
            //session.setAttribute("UserID",id);
           // response.sendRedirect("loginOK.jsp");
            //페이지를 저장 하는 객체를 만듬
            //RequestDispatcher RD=request.getRequestDispatcher("loginOK.jsp");
            //객체를 이용하여 다음페이지로 넘어감
            //RD.forward(request, response);
           
         }else if(cmd.equals("logout")==true) {
        	 doLogout(request, response);
        	 
         }else if(cmd.equals("reg")==true) {
        	 
        	 doReg(request, response);
         }else if(cmd.equals("idCheck")==true) {
        	 doIdCheck(request, response);
         }
         else{
           // System.out.println("로그인실패");
            response.sendRedirect("loginFail.jsp");
         }
      } 
      catch (SQLException e) 
      {
   
    	  response.sendRedirect("SQLFail.jsp");
      
      } catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
   }
   
   void doIdCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
	   
	   PrintWriter out = response.getWriter();
	   
	   String id = request.getParameter("id");
	   
	   if(dao.idCheck(id)==true) {//사용가능한 아이디
		   
		   out.println("{\"ret\":"+true+"}"); //JSON 형식 데이타
	   
	   }else{
		   out.println("{\"ret\":"+false+"}");
		   
	   }
			   
	
   }
   
   void doReg(HttpServletRequest request, HttpServletResponse response) 
		   throws SQLException, IOException, 
		   UnsupportedEncodingException, ClassNotFoundException {
	   
	   //한글이 깨진다!
	  //request.setCharacterEncoding("UTF-8");
	   //getParameter 하기 전에 필요하므로 doGet 메소드에 !
	   
	   //(1) DAO를 이용해서 회원정보를 데이타버에스에 추가
	   String id=request.getParameter("id");
	   String pw=request.getParameter("pw");
	   String name = request.getParameter("name");
	   String addr = request.getParameter("addr");
//	   System.out.println("id : "+id);
//	   System.out.println("pw : "+pw);
//	   System.out.println("name : "+name);
//	   System.out.println("address : "+addr);
//	
	  if( dao.reg(id, pw, name)==true){
		  //(2) 로그인 상태로 만듦
		  HttpSession session = request.getSession(); 
		  
		  session.setAttribute("UserID", id);
		  response.sendRedirect("index.jsp");
		   
		   //(3) 회원가입 후의 페이지로 이동함
	  }else{
		  //데이터베이스에 회원정보 추가 실패
			
	  }
	   
   }
  
   
   //로그인 기능 수행 메소드  - 로그인 기능 매핑메소드
   void doLogin(HttpServletRequest request, HttpServletResponse response) 
		   throws SQLException, IOException {
	   
	   HttpSession session = request.getSession();
	   
	   String id = request.getParameter("id");
	   String pw = request.getParameter("pw");
	   
	   if(dao.login(id, pw)==true) {
		   
		   session.setAttribute("UserID", id);
		   response.sendRedirect("loginOK.jsp");
		   
	   }else {
		   
		   response.sendRedirect("loginFail.jsp");
	   }
   }
   
   void doLogout(HttpServletRequest request, HttpServletResponse response) 
		   throws SQLException, IOException {
	 
	  HttpSession session = request.getSession();
	  
	  //세션에서 값을 선택해서 제거
	 // session.removeAttribute("UserID");
	  
	  //세션에 저장한 모든 값을 제거
	  session.invalidate();
	  
	  response.sendRedirect("index.jsp");
	  
   }

   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      System.out.println("-------dopost 메소드------");
      doGet(request, response);  //doGet 메소드내용을 복사하기보다 이렇게 써줌으로써 사용함
   }

}