package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ReplyDAO;
import dao.UserDAO;
import dto.BoardDTOOut;
import dto.ListDTOOut;
import dto.ListMainDTOOut;
import dto.PageDTOOut;
import dto.ReplyDTOIn;
import dto.ReplyDTOOut;
import dto.UserDTOOut;
import dto.pageDTOIn;


@WebServlet("*.user")
public class UserCtrl extends HttpServlet {


	UserDAO dao = new UserDAO();
	ReplyDAO daoReply = new ReplyDAO();

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
   	String word = request.getParameter("search").trim();
   	String type = request.getParameter("type");
   	int mode=0;
   	
   //	word = null;
//	System.out.println("...word:"+word);
//	System.out.println("...word:"+pageNo);
//	System.out.println("...word:"+pageSize);
   	
  System.out.println("type은  "+type);
	if(type.equals("trip")==true){
		type = "관광지";
	}else if(type.equals("restaurant")==true){
		type = "맛집";
	}else if(type.equals("specialty")==true){
		type = "특산물";
	}
   	
   	if(word == null || word.equals("null")==true || word.equals("")==true){   // word.equals("null")==true  도 해줘야해 ㅠㅠㅠㅠ
		System.out.println("모드는 -1이어야함");
		
		dto = new pageDTOIn(pageNo, pageSize, type);
	}else{
		System.out.println("모드는 0이어야함");
	
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
      String clickNum =request.getParameter("num");
      
      HttpSession session = request.getSession();
	  String id = (String) session.getAttribute("USERID");
	  System.out.println("user:"+id);
      System.out.println("user 글 읽어오기 명령어와 글 번호 : "+num);
      
//      int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//      int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//      int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//      int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//    이미 request에 담겨있는것들
      
      Cookie[] cookies = request.getCookies();
    
//      String ip = request.getRemoteAddr(); ip주소
      
      if(cookies==null || cookies.length==0){
    	  
    	   Cookie c= new Cookie(id, clickNum);  //하나의 아이디에 조회한 글번호 저장
          c.setMaxAge(60*60*6) ;  //6시간
          System.out.println("cookie id="+c.getName());
          response.addCookie(c) ;
      }
      
      int shit = -1;
      
      
      for(int i=0; i < cookies.length; i++){  
    	  
    	  String cName = cookies[i].getName();
    	  System.out.println("**cName="+cName);
    	  String cValue = cookies[i].getValue() ;
    	  System.out.println("**cValue="+cValue);
    	  
    	  if(cName.equals(id)==true){  //이미 아디이의 쿠키이름이 존재할때
    		  
    		  shit =0;
    		  
              System.out.println("***이미 아이디 쿠키이름 있음");

    		
    		  System.out.println("***cValue="+cValue);

        	  String[] values = cValue.split("and");
        	  
        	  int shit2=-1;
        	  
    		  for(int j=0; j<values.length ; j++){
    			  
    			  System.out.println("쿠키값 길이:"+values.length);
    			 System.out.println("**쿠키값:"+values[j]);
    			 System.out.println("**게시글:"+clickNum);
        		  if(values[j].equals(clickNum)==true){ //쿠키값이 이미 조회한 글넘버랑 같으면 
        			  
        			  System.out.println("****쿠키값:"+values[j]+"게시글:"+clickNum);
        		        
        			  shit2 = 0;
        			  
        			  System.out.println("이미 조회한 글입니다");
        	          
                  }else{
                  }
        		
    		  }
    		//글넘버가 같은게 하나도 없다면
        	  if(shit2 == -1){
        		  
        		  System.out.println("***클릭전 쿠키값:"+cValue);
        		  
        		  cValue = cValue + "and"+clickNum;  //쿠키값 문자열에 넘버를 추가  !!!!ㅠㅠㅠㅠㅠ세시간삽질이유: 쿠키값에는 "," 이나 ";" 사용안됨 ㅠㅠ
        		  
        		  System.out.println("***클릭후 쿠키값:"+cValue);
        		  
        		  System.out.println(id);
        		  
        		   Cookie c = new Cookie(id, cValue);  //수정

        		  System.out.println("***수정된 쿠키값:"+c.getValue()+"  이름:"+c.getName());
        		 
        		 
    	          daoReply.click(num);//조회수 증가
    	        
                  System.out.println("***조회수증가!!!");
                  
                  response.addCookie(c);

        	  }
        	 
    	  }else{
             
    	  }
    	
      }
      
      if(shit==-1){   
    	 
    	  System.out.println("***아이디 쿠키 이름 없음");

    	 Cookie c = new Cookie(id, clickNum);
		  c.setMaxAge(60*60*6) ;  //6시간
          System.out.println("cookie id="+c.getName());
         
          System.out.println("***새쿠키생성");
          daoReply.click(num);//조회수 증가
          System.out.println("***조회수증가");
        response.addCookie(c) ;
      }
      
     
	  
		 
      
      BoardDTOOut dto = dao.read(num); //num에 해당하는 글을 읽어옴
      
      ArrayList<ReplyDTOOut> list = daoReply.read(num); //num에해당하는 댓글 
      request.setAttribute("ReplyList", list);
      
      request.setAttribute("BOARDDTOOUT", dto);
      
      forward(request, response, "userView.jsp");
      
   }
   
  
   public void replyWrite(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
   {
	  
	   int num = Integer.parseInt(request.getParameter("num"));
	   String content = request.getParameter("content").trim();
	   HttpSession session = request.getSession();
	   String id = (String) session.getAttribute("USERID");
	   
     int pageNo = Integer.parseInt(request.getParameter("pageNo"));
     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
     int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
     int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
     String word = request.getParameter("search");
//	   
	   ReplyDTOIn dto = new ReplyDTOIn(num, content, id);
	   
	   if(daoReply.write(dto)==true){
		   System.out.println("댓글등록완료: num="+num+"  content="+content+"  id="+id);
		   sendReDirect(response, "read.user?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&search="+word);
		  
	   }else{
		
	   }
	   
   }
   
   public void replyDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   PrintWriter out = response.getWriter();
	  
	   int rpNum = Integer.parseInt(request.getParameter("rpNum"));  //삭제할 댓글 고유번호
	  
	  
	   System.out.println("삭제할댓글번호:"+rpNum);
//	   
//	   int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//	     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//	     int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//	     int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//	     String word = request.getParameter("search");
//	   
	   if(daoReply.del(rpNum)==true){
		   System.out.println("해당 댓글이 삭제되었습니다");
		   out.print("{\"ret\":true}");
		   
    	}else{
    		out.print("{\"ret\":false}");
    	}
//		   sendReDirect(response, "read.user?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&search="+word);
	   
   }
   
   public void replyMod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   int rpNum = Integer.parseInt(request.getParameter("rpNum"));  //삭제할 댓글 고유번호
		
	   PrintWriter out = response.getWriter();

	  
	   String content = request.getParameter("content");
	   
	   System.out.println("수정할 댓글 번호:"+rpNum);
	   
	   if(daoReply.mod(rpNum, content)==true){
		   System.out.println("해당 댓글이 수정되었습니다");
		   out.print("{\"ret\":true}");
		   
    	}else{
    		out.print("{\"ret\":false}");
    	}
	    	 
   }
   
   public void nice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   int num = Integer.parseInt(request.getParameter("num"));
	   String type = request.getParameter("type");
	   String id = request.getParameter("id");
	   
	   ////////////    쿠키만들기로 조회수 기능
//	   PrintWriter out = response.getWriter();
//	   
//	   String cmd = "read";
//	   String table = "user";
//	   int num = Integer.parseInt(request.getParameter("num"));
//	   String id = request.getParameter("id");
//	   
//	   String key = cmd + "|" + table + "|" + num +"|" + id;
//	   String value = "view";
//	   
//	  if( checkCookie(key, value, request)==true){
//	
//		  makeCookie(key, value, response);
//		  out.print("{\"ret\":true, \"view\":true}");
//		  
//	  }else{
//		  System.out.println("이미 조회한글");
//	  }
	   
	   
	   /////////////////////////////////////////////
	
		   System.out.println("좋아요 한 글번호:"+num);

	  	   PrintWriter out = response.getWriter();
		  	   
	  	   if(daoReply.nice(num, type,id)==true){
	  		   
	  		   out.print("{\"ret\":true}");
	  		   
	      }else{
	      		out.print("{\"ret\":false}");
	      }
		
   }
   
  public void niceDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	  HttpSession session = request.getSession();
	 
	   int num = Integer.parseInt(request.getParameter("num"));
	  
	  // int niceNum = Integer.parseInt(request.getParameter("niceNum"));
	   
	
	   System.out.println("좋아요 취소한 글번호:"+num);

  	   PrintWriter out = response.getWriter();
	  	   
  	   if(daoReply.niceDel(num)==true){
  		   
  		   out.print("{\"ret\":true}");
  		   
      }else{
      		out.print("{\"ret\":false}");
      }
	
  }
  
  void makeCookie(String key, String value, HttpServletResponse response){
	  
	  Cookie cookie = new Cookie(key, value);
	  
	  cookie.setMaxAge(60*60*24);
	  
	  response.addCookie(cookie); //쿠키를 브라우저에 전달/저장
	  
  }
  
  boolean checkCookie(String key, String value, HttpServletRequest request){
	  
	  Cookie[] cookies = request.getCookies();
	  
	  for(int i=0; i<cookies.length ;i++){
		  String cKey = cookies[i].getName();
		  String cValue = cookies[i].getValue();
		  
		  if(cKey.equals(key) ==true && cValue.equals(value)==true){
			  
			  return true;  //원하는 쿠키가 존재
		  }
		  
	  }
	  return false;
  }
  
  
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String cmd = parseCommand(request);
      request.setCharacterEncoding("UTF-8");
   
      try{
         if(cmd.equals("main.user")==true)
         {
            main(request,response);
            System.out.println("유저 들어옴");
         }else if(cmd.equals("list.user")==true){
        	 list(request, response);
         }else if(cmd.equals("read.user")==true){
        	 read(request, response);
         }else if(cmd.equals("replyWrite.user")==true){
        	 replyWrite(request, response);
         }else if(cmd.equals("replyDel.user")==true){
        	 replyDel(request, response);
         }else if(cmd.equals("replyMod.user")==true){
        	 replyMod(request, response);
         }else if(cmd.equals("nice.user")==true){
        	 nice(request, response);
         }else if(cmd.equals("niceDel.user")==true){
        	 niceDel(request, response);
         }
//         }else if(cmd.equals("makeCookie.user")==true){
//        	 System.out.println("쿠키만들기");
//        	 
//        	 //1.쿠키 객체 생성하기
//        	 //nice클릭 확인으 ㄹ 위한 유일한 키 생성
//        	 String command = "nice";
//        	 String table = "user";
//        	 int num = 80;
//        	 String id = "test";
//        	 
//        	 String key = command + "|" + table + "|" + num +"|" + id;
//        	 
//        	 Cookie c = new Cookie(key, "nice");
//        	// Cookie c = new Cookie("name", "choikyungeun");
//        	 
//        	 
//        	 //2.쿠키가 유죄되는 시간이 설정
//        	 c.setMaxAge(60*60*24*365);
//        	 //3.쿠키를 톰캣에서 브라우저로 보낼때 response에 저장하여 보냄
//        	 
//         }else if(cmd.equals("readCookie.user")==true){
//        	 System.out.println("쿠키 읽어오기");
//        	 
//         }
//         
      }catch(SQLException e){
    	  
      }
         
      
         
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      doGet(request, response);
   }

}