import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import member.DAO;

@WebServlet(name = "Controller", urlPatterns = { "/Controller" })
public class Controller extends HttpServlet
{
   DAO dao; //모델 객체 저장 변수(참조변수)
   
   public Controller() throws ClassNotFoundException, SQLException
   {
	   dao= new DAO(); //DAO 객체 생성 (생성자) 
   }
   
   //로그인 기능 수행 메소드.. 로그인 기능 매핑(login<==>doLogin)
   void doLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
   {
	   HttpSession session=request.getSession();
	   
	   String id=request.getParameter("id");
	   String pw=request.getParameter("pw");
	   if(dao.login(id, pw)==true)
	   {
		   session.setAttribute("UserID",id);
		   response.sendRedirect("loginOK.jsp");
		  
	   }else
	   {
		  // System.out.println("로그인실패");
		   response.sendRedirect("loginFail.jsp");
	   }
   }
   //로그아웃 기능 수행 메소드 : logout<==>doLogout
   void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException
   {
	   HttpSession session=request.getSession();
	   
	   //세션에서 저장된 특정값을 선택해서 제거
	   session.removeAttribute("UserID");
	   
	   //세션에 저장된 모든 값을 제거 
	   session.invalidate();
	  
	   //페이지 이동
	   response.sendRedirect("index.jsp");
	   
   }
   
   void doReg(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException
   {
	   //한글이 깨진다!
	   //request.setCharacterEncoding("UTF-8");
	   //getParameter 하기 전에 필요하므로 doGet 메소드에 !
	      
	   //(1) DAO를 이용해서 회원 정보를 데이터 베이스에 추가
	   String id=request.getParameter("id");
	   String pw=request.getParameter("pw");
	   String name=request.getParameter("name");
	   String address1=request.getParameter("address1");
	   //한글이 잘나오는지 확인해야함
//	   System.out.println("id : "+id);
//	   System.out.println("pw : "+pw);
//	   System.out.println("name : "+name);
//	   System.out.println("address : "+address1);
	   if(dao.reg(id, pw, name)==true)
	   {
		  HttpSession session = request.getSession();
		//(2) 로그인 상태로 만듬
		   session.setAttribute("UserID",id);
		   response.sendRedirect("loginOK.jsp");
		   //(3) 회원가입 후의 페이지로 이동함
	   }else //데이터 베이스에 회원 정보 추가 실패
	   {
		   
	   }
	 
   }
   //아이디 중복 체크 메소드
   void doIdCheck(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
   {
	   PrintWriter out = response.getWriter();
	
	   String id = request.getParameter("id");
	   if(dao.idCheck(id)==true) //사용가능한 아이디
	   {
		   out.println("{\"ret\":"+true +"}"); //JSON 형식 데이터 
		   
	   }else //사용 불가능한 아이디
	   {
		   out.println("{\"ret\":"+false +"}"); //JSON 형식 데이터
	   }
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
	   request.setCharacterEncoding("UTF-8");
	   response.setContentType("text/html; charset=UTF-8");
	   
	   String cmd=request.getParameter("cmd");
	   System.out.println("cmd="+cmd);
	   
	   //세션과 리퀘스트의 차이점을 알아두자!! 중요!!
	   //세션 객체 가져오기
	   HttpSession session=request.getSession();
	   
	   String id=request.getParameter("id");
	   String pw=request.getParameter("pw");
	   
	   System.out.println("-------doget 메소드------");
	   
//	   System.out.println("id "+id);
//	   System.out.println("pw "+pw);
	   try 
	   {
		   if(cmd.equals("login")==true)
		   {
			   doLogin(request,response); //로그인
		   }else if(cmd.equals("logout")==true)
		   {
			   doLogout(request,response);  //로그아웃
		   }else if(cmd.equals("reg")==true)
		   {
			   doReg(request,response);
		   }else if(cmd.equals("idCheck")==true) //아이디 중복체크
		   {
			   doIdCheck(request,response);
//		   if(dao.login(id, pw)==true)
//		   {
//			   //request 객체를 이용해서 값을 전달함
//			   request.setAttribute("UserID", id);
//			   
//			   //session객체를 이용해서 값을 전달함
//			   session.setAttribute("UserID",id);
//			   response.sendRedirect("loginOK.jsp"); //페이지를 출력해줌 session에서는 sendRedirect가 필요함.. 페이지를 불러와야하니까 ...
//			   //페이지를 저장 하는 객체를 만듬
//			   RequestDispatcher RD=request.getRequestDispatcher("loginOK.jsp");
//			   //객체를 이용하여 다음페이지로 넘어감
//			   RD.forward(request, response); // 리퀘스트방식은 sendRedirect가 필요없음 왜냐면 RD객체에 이미 loginOK.jsp를 저장해주었으니까
			  
		   }else
		   {
			  // System.out.println("로그인실패");
			   response.sendRedirect("loginFail.jsp");
		   }
	   } 
	   catch (SQLException e) 
	   {
	
		System.out.println("SQL 에러 : "+e.getMessage());
	   } catch (ClassNotFoundException e) {
		
		System.out.println("에러 : "+e.getMessage());
	}
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
	   System.out.println("-------dopost 메소드------");
	   doGet(request, response);  //doGet 메소드내용을 복사하기보다 이렇게 써줌으로써 사용함
   }

}