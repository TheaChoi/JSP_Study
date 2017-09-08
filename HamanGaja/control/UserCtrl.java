package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dto.BoardDTOOut;
import dto.ListDTOOut;
import dto.ListMainDTOOut;
import dto.PageDTOOut;
import dto.UserDTOOut;
import dto.pageDTOIn;


@WebServlet("*.user")
public class UserCtrl extends HttpServlet {

       
    UserDAO dao = new UserDAO();
    public UserCtrl() {
       
       
    }
    
    String parseCommand(HttpServletRequest request)
   {
      //request 객체에서 uri 경로만을 가져옴.
      String uri = request.getRequestURI();
      String path = request.getContextPath();
      String cmd = uri.substring(path.length()+1);
      System.out.println("uri: " + uri);
      System.out.println("path: " + path);
      
      return cmd;
   }
   
   //페이지 이동방법은 2가지  //로그인은 세션을 사용하기때문에 어떤 방법을 사용하던지 상관x 다만 정보를 계속 가져가야하는 게시판은 forward 방법이 좋음 
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
   void main(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
       System.out.println("main메소드 들어옴");
       
       ArrayList<ListMainDTOOut> dto =  (ArrayList<ListMainDTOOut>) dao.listmain("관광지", 4);
       
       request.setAttribute("LISTMAINDTO", dto);
       
       forward(request, response, "main.jsp");
    }
   
   public void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
	   
	   pageDTOIn dto;
   	
   	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
   	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
   	int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
   	int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
   	String word = request.getParameter("search");
   	String type = request.getParameter("type");
   	int mode = 0;
   	
  System.out.println("type은  "+type);
	if(type.equals("trip")==true){
		type = "관광지";
	}else if(type.equals("restaurant")==true){
		type = "맛집";
	}else if(type.equals("specialty")==true){
		type = "특산물";
	}
   	
   	if(word==null){
		
		dto = new pageDTOIn(pageNo, pageSize, type);
	}else{
		dto = new pageDTOIn(pageNo, pageSize, mode, word, type);
	}
	
   	ArrayList<UserDTOOut> list = (ArrayList<UserDTOOut>) dao.list(dto);
	
   	
   	/////////////////////////////////////////////////////////////////////////
   	
   	
   	int total = dao.countAll(type, dto);
   	
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
    System.out.println("type은  "+type);
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
    
    /////////////////////////////////////////////////////////////////
    
//    if(type.equals("관광지")==true){
//		request.setAttribute("TRIP", list);
//		//forward로 페이지를 이동함
//    	forward(request, response, "trip.jsp");
//	}else if(type.equals("맛집")==true){
//		request.setAttribute("RESTAURANT", list);
//		forward(request, response, "restaurant.jsp");
//	}else if(type.equals("특산물")==true){
//		request.setAttribute("SPECIALTY", list);
//		forward(request, response, "specialty.jsp");
//	}
//   	
    
    request.setAttribute("LIST", list);
    forward(request, response, "list.jsp");
   	
   }
   
   void read(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
      
      int num = Integer.parseInt(request.getParameter("num"));
      System.out.println("user 글 읽어오기 명령어와 글 번호 : "+num);
      
//      int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//      int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//      int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//      int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//    이미 request에 담겨있는것들
      
      BoardDTOOut dto = dao.read(num); //num에 해당하는 글을 읽어옴
      
      request.setAttribute("BOARDDTOOUT", dto);
      forward(request, response, "userView.jsp");
   }
   
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String cmd = parseCommand(request);
   
      try{
         if(cmd.equals("main.user")==true)
         {
            main(request,response);
            System.out.println("유저 들어옴");
         }else if(cmd.equals("list.user")==true){
        	 list(request, response);
         }else if(cmd.equals("read.user")==true){
        	 read(request, response);
         }
       
         
      }catch(SQLException e){
    	  
      }
         
      
         
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}