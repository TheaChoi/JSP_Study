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
   DAO dao; //�� ��ü ���� ����(��������)
   
   public Controller() throws ClassNotFoundException, SQLException
   {
      dao= new DAO(); //DAO ��ü ���� (������) 
   }
   
   
  
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
	   
	   //�ѱ��� ������!
	   request.setCharacterEncoding("UTF-8");
	   response.setContentType("text/html; charset=utf-8");
	   
	   String cmd = request.getParameter("cmd");
	   System.out.println("cmd="+cmd);
	   
      //���ǰ� ������Ʈ�� �������� �˾Ƶ���!! �߿�!!
      //���� ��ü ��������
   //   HttpSession session=request.getSession();
      
      String id=request.getParameter("id");
      String pw=request.getParameter("pw");
      
      System.out.println("-------doget �޼ҵ�------");
      
//      System.out.println("id "+id);
//      System.out.println("pw "+pw);
      try 
      {
         //if(dao.login(id, pw)==true)
    	  if(cmd.equals("login")==true)
         {
    		  doLogin(request, response);
            //request ��ü�� �̿��ؼ� ���� ������
            //request.setAttribute("UserID", id);
            
            //session��ü�� �̿��ؼ� ���� ������
            //session.setAttribute("UserID",id);
           // response.sendRedirect("loginOK.jsp");
            //�������� ���� �ϴ� ��ü�� ����
            //RequestDispatcher RD=request.getRequestDispatcher("loginOK.jsp");
            //��ü�� �̿��Ͽ� ������������ �Ѿ
            //RD.forward(request, response);
           
         }else if(cmd.equals("logout")==true) {
        	 doLogout(request, response);
        	 
         }else if(cmd.equals("reg")==true) {
        	 
        	 doReg(request, response);
         }else if(cmd.equals("idCheck")==true) {
        	 doIdCheck(request, response);
         }
         else{
           // System.out.println("�α��ν���");
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
	   
	   if(dao.idCheck(id)==true) {//��밡���� ���̵�
		   
		   out.println("{\"ret\":"+true+"}"); //JSON ���� ����Ÿ
	   
	   }else{
		   out.println("{\"ret\":"+false+"}");
		   
	   }
			   
	
   }
   
   void doReg(HttpServletRequest request, HttpServletResponse response) 
		   throws SQLException, IOException, 
		   UnsupportedEncodingException, ClassNotFoundException {
	   
	   //�ѱ��� ������!
	  //request.setCharacterEncoding("UTF-8");
	   //getParameter �ϱ� ���� �ʿ��ϹǷ� doGet �޼ҵ忡 !
	   
	   //(1) DAO�� �̿��ؼ� ȸ�������� ����Ÿ�������� �߰�
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
		  //(2) �α��� ���·� ����
		  HttpSession session = request.getSession(); 
		  
		  session.setAttribute("UserID", id);
		  response.sendRedirect("index.jsp");
		   
		   //(3) ȸ������ ���� �������� �̵���
	  }else{
		  //�����ͺ��̽��� ȸ������ �߰� ����
			
	  }
	   
   }
  
   
   //�α��� ��� ���� �޼ҵ�  - �α��� ��� ���θ޼ҵ�
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
	  
	  //���ǿ��� ���� �����ؼ� ����
	 // session.removeAttribute("UserID");
	  
	  //���ǿ� ������ ��� ���� ����
	  session.invalidate();
	  
	  response.sendRedirect("index.jsp");
	  
   }

   
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   {
      System.out.println("-------dopost �޼ҵ�------");
      doGet(request, response);  //doGet �޼ҵ峻���� �����ϱ⺸�� �̷��� �������ν� �����
   }

}