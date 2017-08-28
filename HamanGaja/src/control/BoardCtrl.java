package control;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import dto.BoardDTOIn;
import dto.ListDTOOut;
import dto.PageDTOOut;
import dto.pageDTOIn;

/**
 * Servlet implementation class BoardCtrl
 */
@WebServlet("*.board")
public class BoardCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	BoardDAO dao = new BoardDAO();
	
    public BoardCtrl() {
        super();
        
    }
    String parseCommand(HttpServletRequest request){
    	//request 객체에서 uri 경로만 가져옴
    	String uri = request.getRequestURI();
    	
    	String cmd = uri.substring(uri.lastIndexOf('/')+1);
    	//System.out.println("uri: "+uri);

    	return cmd;
    }
    
    void sendReDirect(HttpServletResponse response, String view) throws IOException //페이지 이동시키는 메소드 //REdirect로 이동함 : request 객체가 새로 만들어짐 그러므로 리스폰만 있으면 됌
    {
       response.sendRedirect(view);
    }

    //forward로 이동 함 : 기존의 request 객체를 전달함 
    void forward(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException // 기존의 리퀘스트를 이용하기 때문에 재사용, 이전에 저장했던 정보가 따라감 
    {
       RequestDispatcher RD = request.getRequestDispatcher(view);
       RD.forward(request, response);
    }
    
    void write(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	
    	String path="D:/upload"; //파일 저장 장소
    	int size=1024*1024*100;//파일크기 100메가
    	String encType="UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, size, encType,new DefaultFileRenamePolicy());//이름중복방지
    	
    	//기타입력정보
    	String type=mr.getParameter("type"); //관광지, 맛집, 특산물
    	String title= mr.getParameter("title");
    	String content=mr.getParameter("content");
    	String filename = mr.getFilesystemName("pic"); //저장파일이름
    	//System.out.println(type+title+content+filename);
    	
    	//DTO 객체를 생성함
        BoardDTOIn dto = new BoardDTOIn(type, title, content, filename);
        //DTO를 DAO 전달해줌
        type = URLEncoder.encode(type, "UTF-8") ;
        if(dao.write(dto)==true){
        	sendReDirect(response, "List.board?pageNo=0&pageSize=5&viewStart=0&viewEnd=0&type="+type);
        }
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	pageDTOIn dto;
    	
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
    	int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
    	
    	String type = request.getParameter("type");
    	type = URLDecoder.decode(type, "UTF-8") ;//get파라미터에 한글이 깨질때
    	
    	int mode = 0;
    	String word = request.getParameter("search");
    	
    	System.out.println("search: "+word);

    	
    	System.out.println("type: "+type);
    	
    	System.out.println("페이지번호:"+pageNo+"  페이지의 글개수:"+pageSize);
    	
    	if(word==null){
    		
    		dto = new pageDTOIn(pageNo, pageSize, type);
    	}else{
    		dto = new pageDTOIn(pageNo, pageSize, mode, word, type);
    	}
    	
    	ArrayList<ListDTOOut> list = dao.listAll(dto, type);
    	
    	//////////////////////////////////////////////////////////////
    	int total   = dao.countAll(type, dto);  
        
        System.out.println("total : " + total);
        
        int pageNum = total/pageSize;
        if(total % pageSize != 0) pageNum++; //페이지 갯수
        
        int prevPage = pageNo-1; //이전 페이지
                           //prevPage가 -1이면 이전페이지는 기능 없음
        
        int nextPage = pageNo+1; //다음 페이지
        
       
        if(nextPage >= pageNum) nextPage=-1; //nextPage가 -1이면 다음페이지 기능 없음
        
        //보여지는 페이지 번호 범위 조절
        if(pageNo > viewEnd-1){
        	viewStart++;
        	viewEnd++;
        }
        
        if(pageNo < viewStart){
        	viewStart--;
        	viewEnd--;
        }
        
        //viewStart와 viewEnd의 범위 확인
        if(viewStart<0) viewStart=0;
        if(viewEnd>pageNum) viewEnd = pageNum;
        
        PageDTOOut pageOut = new PageDTOOut(   pageNo,      //현재 페이지 번호
                                pageSize,    //페이지의 글의 개수
                                total,       //전체 글의 개수
                                pageNum,   //페이지 개수
                                prevPage,    //이전 페이지 번호
                                nextPage, //다음 페이지 번호
                                word,
                                viewStart,viewEnd);  
        
        request.setAttribute("PAGEOUT", pageOut);
    	
    	//list를 jsp페이지에 전달하기 위해서..request객체에 저장함
    	if(type.equals("관광지")==true){
    		request.setAttribute("TRIP", list);
    		//forward로 페이지를 이동함
        	forward(request, response, "tripList.jsp");
    	}else if(type.equals("맛집")==true){
    		request.setAttribute("RESTAURANT", list);
    		forward(request, response, "restaurantList.jsp");
    	}else if(type.equals("특산물")==true){
    		request.setAttribute("SPECIALTY", list);
    		forward(request, response, "specialtyList.jsp");
    	}
    	
    	
    }
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = parseCommand(request);
		System.out.println(cmd);
		try{	
			if(cmd.equals("write.board")==true){
				write(request, response);
			}else if(cmd.equals("List.board")==true){
				list(request,response);
			}
		}
			catch(SQLException e){
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
