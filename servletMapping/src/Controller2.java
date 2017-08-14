

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
		System.out.println("게시판 관리 서블릿 연결");
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String cmd = uri.substring(path.length()+1);
		//cmd에 uri(주소)를 path(JSPtest)의 길이만큼 자른 
		//내용(Controller/...)을 저장한다.
        //+1을 더한이유는 '/'까지 빼기위해서 
		
		System.out.println("uri : "+uri);
		System.out.println("path : "+path);
		System.out.println("cmd : "+cmd);
		
		if(cmd.equals("write.board")==true) {
			System.out.println("게시판 쓰기 실행");
		}else if(cmd.equals("read.board")==true) {
			System.out.println("게시판 읽기 실행");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
