

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.mem")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Controller() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("ȸ�� ���� ���� ����");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String cmd = uri.substring(path.length()+1);
		//cmd�� uri(�ּ�)�� path(JSPtest)�� ���̸�ŭ �ڸ� 
		//����(Controller/...)�� �����Ѵ�.
        //+1�� ���������� '/'���� �������ؼ� 
		
		System.out.println("uri : "+uri);
		System.out.println("path : "+path);
		System.out.println("cmd : "+cmd);
		
		if(cmd.equals("login.mem")==true) {
			System.out.println("�α��� ���� ����");
		}else if(cmd.equals("reg.mem")==true) {
			System.out.println("ȸ������ ���� ����");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
