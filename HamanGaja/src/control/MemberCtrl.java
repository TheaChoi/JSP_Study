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
import dto.ModDTOIn;
import dto.infoDTOOut;


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
    
    void pwCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    
    	String pw = request.getParameter("pw");
    	System.out.println("pwChek:"+pw);
    	PrintWriter out = response.getWriter();
    	if(dao.pwCheck(pw)==true){
    		out.print("{\"ret\":true}");
    		System.out.println("비밀번호 같은");
    		out.close();
    	}else{
    		out.print("{\"ret\":false}");
    		System.out.println("비밀번호 다름");
    		out.close();
    		
    	}
    }
    
    void del(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
    	
    	//1. 회원탈퇴 아이디를 가져옴
    	HttpSession session = request.getSession();
    	String UserId = (String)session.getAttribute("USERID");
    	PrintWriter out = response.getWriter();
    	System.out.println("회원탈퇴 아이디:"+UserId);
    	if(dao.del(UserId)==true){
    		//세션에 저장해 놓은 로그인 정보 기타 개인정보를 모두 삭제
    		session.invalidate();
			out.print("{\"ret\":true}");
			sendRedirect(response, "main.jsp");
    	}else{
    		out.print("{\"ret\":false}"); //json형식을 out객체로 출력
        	
    	}
    }
    
    void info(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
    	
    	HttpSession session = request.getSession();
    	String USERID = (String)session.getAttribute("USERID");
    	infoDTOOut dto = dao.info(USERID); //회원정보를 가지고 있는 DTO를 반환 받음
    	
    	//회원정보 DTO를 modInfo.jsp로 전달
    	request.setAttribute("infoDTOOut", dto);
    	forward(request, response, "modInfo.jsp");
    }
    
    void mod(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	//1.세션 내장객체를 가져오고, 세션 객체에 있는 로그인 아이디 정보를 가져옴
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String USERID = (String)session.getAttribute("USERID");
    	//2.변경할 개인정보
    	String pw = request.getParameter("pw1");
    	String phone = request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3");
    	String email = request.getParameter("emailid")+"@"+request.getParameter("emailser");
    	
    	ModDTOIn dto = new ModDTOIn(USERID, pw, phone, email);
    	
    	if(dao.mod(dto)==true){
    		System.out.println("회원정보수정 완료");
    		out.print("{\"ret\":true}");
    		
    	}else{
    		System.out.println("회원정보 수정 실패");
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
			}else if(cmd.equals("idCheck.mem")==true){ //아이디 중복체크
				idCheck(request, response);
			}else if(cmd.equals("pwCheck.mem")==true){
				pwCheck(request, response);
			}else if(cmd.equals("info.mem")==true){

				info(request, response);
			}else if(cmd.equals("del.mem")==true){  //회원탈퇴
				del(request, response);
			}else if(cmd.equals("mod.mem")==true){
				mod(request, response);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
