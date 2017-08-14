package board;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BoardDAO dao;
	
    public BoardController() throws IOException {
        super();
        dao = new BoardDAO();
      }
    
    String parseCommand(HttpServletRequest request) {
    	
    	String uri = request.getRequestURI();
    	String path = request.getContextPath();
    	String cmd = uri.substring(path.length()+1);
    	return cmd;
    }
    
    void write(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       //(1)테스트용 DTO 객체 생성
      String title = request.getParameter("title");
      String content = request.getParameter("content");
            
      System.out.println("제목 : "+title+"내용 : "+content);      // 글씨가 깨지는지 확인후 request.setCharacterEncoding("UTF-8"); 써넣기 (깨지면)
         
      BoardDTOIn dto = new BoardDTOIn(title,content);
      if(dao.write(dto)==true) {
    	  //정상적으로 글이 데이터베이스에 저장됨
    	  //글목록가져와서 목록을 jsp view까지 전달
    	  
    	  //(1)바로 list 메소드를 직접 호출한다 (새로고침하면글쓰기부터시작)
    	  //list(request,response);
    	  
    	  //(2) response의 sendRedirect메소드 이용,  새로고침해도 글쓰기부터 시작하지 않는 방법!
    	  response.sendRedirect("list.bo");
    	  
    	  //(3) requestDispatcher 를 이용 (새로고침하면글쓰기부터시작)
    	 // RequestDispatcher RD = request.getRequestDispatcher("list.bo?pageNo=0&pageSize=5");
    	 // RD.forward(request, response);
    	  
    	  
      }else{//데이터베이스에 글이 저장되지 못함
    	  
      }
    }
    
    void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//List list = dao.list();
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	
    	List list = dao.list(pageNo, pageSize);
    	
    	int total = (int)dao.count();  //글의 총개수
    	int pageNum = total/pageSize;
    	if(total%pageSize != 0) pageNum++; //페이지 개수
    	
    	int prevPage = pageNo-1; //이전 페이지 ,  이전페이지가 -1이면 이전페이지 기능 없음
    	int nextPage = pageNo+1;//다음 페이지
    	if(nextPage >= pageNum) nextPage=-1; //nextPage가 -1이면 다음페이지기능 없음
    	
    	PageOut pageOut = new PageOut(pageNo, pageSize, total, pageNum, prevPage, nextPage);
    	
    	request.setAttribute("PAGEOUT", pageOut);
    	
    	request.setAttribute("LIST", list);
    	
    	RequestDispatcher RD = request.getRequestDispatcher("list.jsp");
    	RD.forward(request, response);
    }
    
    void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int num = Integer.parseInt(request.getParameter("num")); //getParameter로 읽어오는 건 모두 String이다
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	
    	System.out.println("글번호:"+num+"  페이지번호:"+pageNo+"   페이지글개수:"+pageSize);
    	
    	ContentDTOOut dto = dao.read(num);
    	
    	request.setAttribute("CONTENTDTOOUT", dto);
    	
    	RequestDispatcher RD = request.getRequestDispatcher("readOne.jsp");
    	RD.forward(request, response);
    }
    
    void del(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	int num = Integer.parseInt(request.getParameter("num"));
    	
    	System.out.println(num+"번 글 삭제하기");
    	dao.del(num);  //글 삭제했당
    	response.sendRedirect("list.bo?pageNo=0&pageSize=5");
    }
      
    void find(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	String word = request.getParameter("word");//검색어
    	int mode = Integer.parseInt(request.getParameter("mode"));//검색모드
    	
    	System.out.println("검색어: "+word);
    	List list = dao.find(word, mode, pageNo, pageSize);
    	request.setAttribute("LIST", list);
    	///////////////////////////////////////////
    	int total = (int)dao.count(word, mode);  //글의 총개수
    	int pageNum = total/pageSize;
    	if(total%pageSize != 0) pageNum++; //페이지 개수
    	
    	int prevPage = pageNo-1; //이전 페이지 ,  이전페이지가 -1이면 이전페이지 기능 없음
    	int nextPage = pageNo+1;//다음 페이지
    	if(nextPage >= pageNum) nextPage=-1; //nextPage가 -1이면 다음페이지기능 없음
    	
    	PageOut pageOut = new PageOut(pageNo, pageSize, total, pageNum, prevPage, nextPage);
    	
    	request.setAttribute("PAGEOUT", pageOut);
    	
    	
    	
    	RequestDispatcher RD = request.getRequestDispatcher("list.jsp");
    	RD.forward(request, response);
  
    	
    }
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String cmd = parseCommand(request);
		System.out.println("서블릿 실행 cmd= "+cmd);
		
		if(cmd.equals("write.bo")==true){
		
			write(request,response);
		}else if(cmd.equals("list.bo")==true){
	    	  list(request, response);
	    }else if(cmd.equals("read.bo")==true){
	    	read(request,response);
	    }else if(cmd.equals("del.bo")==true){
	    	del(request,response);
	    }else if(cmd.equals("find.bo")==true){
	    	find(request, response);
	    }
	 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
