package control;

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

import dao.MemDAO;
import dto.MemDTOIn;


@WebServlet("*.mem")
public class MemberCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MemDAO dao = new MemDAO();   
   
    public MemberCtrl() {
        super();
       
       
    }
    
    //전체경로에서 필요한 명령어만 추출
    String parseCommand(HttpServletRequest request){
    	//request 객체에서 uri 경로만 가져옴
    	String uri = request.getRequestURI();
    	String path = request.getContextPath();
    	String cmd = uri.substring(path.length()+1);
    	System.out.println("uri: "+uri);
    	System.out.println("path: "+path);
    	return cmd;
    }
    
    //Redirect로 이동함 : request 객체가 새로 만들어짐 (로그인은 세션을 이용하므로 어느것을 사용해도 무방)
    void sendRedirect(HttpServletResponse response, String view) throws IOException {
    	
    	response.sendRedirect(view);
    }
    
    //forward로 이동함 : 기존의 request객체를 전달함
    void forward(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException{
    	
    	RequestDispatcher RD = request.getRequestDispatcher(view);
    	RD.forward(request, response);
    }
    
    void reg(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw1");
    	String phone = request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+ request.getParameter("tel3");
    	String email = request.getParameter("emailid")+"@"+request.getParameter("emailser");
    	String address = request.getParameter("address1")+" "+request.getParameter("address2");
    	
//    	System.out.println(id);
//    	System.out.println(pw1);
//    	System.out.println(tel1);
//    	System.out.println(tel2);
//    	System.out.println(tel3);
//    	System.out.println(emailid);
//    	System.out.println(emailser);
//    	System.out.println(address1);
//    	System.out.println(address2);
//    	
    	MemDTOIn dto = new MemDTOIn(id, pw, phone, email, address);
    	//dto를 dao에 전달해서 데이터베이스에 정보를 저장함
    	if(dao.reg(dto)==true){
    		//로그인페이지로이동할 경우
    		//sendRedirect(response, "login.jsp");
    		
    		//1. 로그인 상태로 만든다.
    		HttpSession session = request.getSession();
    		session.setAttribute("USERID", id);
    		session.setAttribute("USERPW", pw);
    		//2.페이지 이동
    		sendRedirect(response, "main.jsp");
    		
    	};
    	
    }
    
    void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw");
    	
    	
    	if(dao.login(id, pw)==true){
	    	HttpSession session = request.getSession();
	    	session.setAttribute("USERID", id);
	    	session.setAttribute("USERPW", pw);
	    
	    	sendRedirect(response, "main.jsp");
    	}else{
    		sendRedirect(response, "loginFail.jsp");
    	}
    	
    }
    
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//1.세션을 가져옴
    	HttpSession session = request.getSession();
    	//2.세션의 데이타를 지움
    	session.invalidate();
//    	session.removeAttribute("USERID");
//    	session.removeAttribute("USERPW");
    	sendRedirect(response, "logoutSuccess.jsp");
    }
    
    void idCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	String id = request.getParameter("id");
    	PrintWriter out = response.getWriter();
    	if(dao.idCheck(id)==true){
    		out.print("{\"ret\":true}");
    	}else{
    		out.print("{\"ret\":false}");
    	}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		//System.out.println("회원관리서블릿");
		String cmd = parseCommand(request);
		System.out.println("cmd: "+cmd);
		try{
			if(cmd.equals("reg.mem")==true){ //회원가입 명령어
				reg(request, response);
			}else if(cmd.equals("login.mem")==true){
				login(request, response);
			}else if(cmd.equals("logout.mem")==true){
				logout(request, response);
			}else if(cmd.equals("idCheck.mem")==true){
				idCheck(request, response);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
