

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.board")
public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Controller2() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("�Խ��� ���� ���� ����");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String cmd = uri.substring(path.length()+1);
		//cmd�� uri(�ּ�)�� path(JSPtest)�� ���̸�ŭ �ڸ� 
		//����(Controller/...)�� �����Ѵ�.
        //+1�� ���������� '/'���� �������ؼ� 
		
		System.out.println("uri : "+uri);
		System.out.println("path : "+path);
		System.out.println("cmd : "+cmd);
		
		if(cmd.equals("write.board")==true) {
			System.out.println("�Խ��� ���� ����");
		}else if(cmd.equals("read.board")==true) {
			System.out.println("�Խ��� �б� ����");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
