package control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;


@WebServlet("*.admin")
public class AdminCtrl extends HttpServlet {	
	private static final long serialVersionUID = 1L;
	
	AdminDAO dao = new AdminDAO();

	public AdminCtrl() {
        super();
        
    }

	 String parseCommand(HttpServletRequest request){
	    	//request ��ü���� uri ��θ� ������
	    	String uri = request.getRequestURI();
	    	
	    	String cmd = uri.substring(uri.lastIndexOf('/')+1);
	    	System.out.println("uri: "+uri);
	
	    	return cmd;
	    }
	
	void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		System.out.println("������ �α��� �ϱ�");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		System.out.println("�α��ξ��̵�:"+id+"  ��й�ȣ:"+pw);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		if(dao.login(id, pw)==true){
			HttpSession session = request.getSession();
			session.setAttribute("USERID", "admin");
			session.setAttribute("ADMIN", "������");
		//	String type="������";
		//	String encText = URLEncoder.encode(type, "UTF-8") ;

		//	response.sendRedirect("List.board?pageNo=0&pageSize=5&type="+encText);
			response.sendRedirect("/HamanGaja/main.jsp");
		}else{
			System.out.println("������ �α��� �ȵ�");
		}
	}
	
	void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/HamanGaja/admin/index.jsp");//������Ʈ���� ���°� ������
	}
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Admin Servlet");
		String cmd=parseCommand(request);
		System.out.println("��ɾ� : "+cmd);
		
		try{
			if(cmd.equals("login.admin")==true){
				login(request, response);
			}else if(cmd.equals("logout.admin")==true){
				logout(request, response);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
