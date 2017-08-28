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
    
    //��ü��ο��� �ʿ��� ��ɾ ����
    String parseCommand(HttpServletRequest request){
    	//request ��ü���� uri ��θ� ������
    	String uri = request.getRequestURI();
    	String path = request.getContextPath();
    	String cmd = uri.substring(path.length()+1);
    	System.out.println("uri: "+uri);
    	System.out.println("path: "+path);
    	return cmd;	
    }
    
    //Redirect�� �̵��� : request ��ü�� ���� ������� (�α����� ������ �̿��ϹǷ� ������� ����ص� ����)
    void sendRedirect(HttpServletResponse response, String view) throws IOException {
    	
    	response.sendRedirect(view);
    }
    
    //forward�� �̵��� : ������ request��ü�� ������
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
    	//dto�� dao�� �����ؼ� �����ͺ��̽��� ������ ������
    	if(dao.reg(dto)==true){
    		//�α������������̵��� ���
    		//sendRedirect(response, "login.jsp");
    		
    		//1. �α��� ���·� �����.
    		HttpSession session = request.getSession();
    		session.setAttribute("USERID", id);
    		session.setAttribute("USERPW", pw);
    		//2.������ �̵�
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
    	//1.������ ������
    	HttpSession session = request.getSession();
    	//2.������ ����Ÿ�� ����
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
    		System.out.println("��й�ȣ ����");
    		out.close();
    	}else{
    		out.print("{\"ret\":false}");
    		System.out.println("��й�ȣ �ٸ�");
    		out.close();
    		
    	}
    }
    
    void del(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
    	
    	//1. ȸ��Ż�� ���̵� ������
    	HttpSession session = request.getSession();
    	String UserId = (String)session.getAttribute("USERID");
    	PrintWriter out = response.getWriter();
    	System.out.println("ȸ��Ż�� ���̵�:"+UserId);
    	if(dao.del(UserId)==true){
    		//���ǿ� ������ ���� �α��� ���� ��Ÿ ���������� ��� ����
    		session.invalidate();
			out.print("{\"ret\":true}");
			sendRedirect(response, "main.jsp");
    	}else{
    		out.print("{\"ret\":false}"); //json������ out��ü�� ���
        	
    	}
    }
    
    void info(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException{
    	
    	HttpSession session = request.getSession();
    	String USERID = (String)session.getAttribute("USERID");
    	infoDTOOut dto = dao.info(USERID); //ȸ�������� ������ �ִ� DTO�� ��ȯ ����
    	
    	//ȸ������ DTO�� modInfo.jsp�� ����
    	request.setAttribute("infoDTOOut", dto);
    	forward(request, response, "modInfo.jsp");
    }
    
    void mod(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	//1.���� ���尴ü�� ��������, ���� ��ü�� �ִ� �α��� ���̵� ������ ������
    	HttpSession session = request.getSession();
    	PrintWriter out = response.getWriter();
    	String USERID = (String)session.getAttribute("USERID");
    	//2.������ ��������
    	String pw = request.getParameter("pw1");
    	String phone = request.getParameter("tel1")+"-"+request.getParameter("tel2")+"-"+request.getParameter("tel3");
    	String email = request.getParameter("emailid")+"@"+request.getParameter("emailser");
    	
    	ModDTOIn dto = new ModDTOIn(USERID, pw, phone, email);
    	
    	if(dao.mod(dto)==true){
    		System.out.println("ȸ���������� �Ϸ�");
    		out.print("{\"ret\":true}");
    		
    	}else{
    		System.out.println("ȸ������ ���� ����");
    		out.print("{\"ret\":false}");
    	}
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		//System.out.println("ȸ����������");
		String cmd = parseCommand(request);
		System.out.println("cmd: "+cmd);
		try{
			if(cmd.equals("reg.mem")==true){ //ȸ������ ��ɾ�
				reg(request, response);
			}else if(cmd.equals("login.mem")==true){
				login(request, response);
			}else if(cmd.equals("logout.mem")==true){
				logout(request, response);
			}else if(cmd.equals("idCheck.mem")==true){ //���̵� �ߺ�üũ
				idCheck(request, response);
			}else if(cmd.equals("pwCheck.mem")==true){
				pwCheck(request, response);
			}else if(cmd.equals("info.mem")==true){

				info(request, response);
			}else if(cmd.equals("del.mem")==true){  //ȸ��Ż��
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
