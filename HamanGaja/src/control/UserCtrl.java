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
		//request ��ü���� uri ��θ��� ������.
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String cmd = uri.substring(path.length()+1);
		System.out.println("uri: " + uri);
		System.out.println("path: " + path);
      
      return cmd;
   }
   
   //������ �̵������ 2����  //�α����� ������ ����ϱ⶧���� � ����� ����ϴ��� ���x �ٸ� ������ ��� ���������ϴ� �Խ����� forward ����� ���� 
   void sendReDirect(HttpServletResponse response, String view) throws IOException //������ �̵���Ű�� �޼ҵ� //REdirect�� �̵��� : request ��ü�� ���� ������� �׷��Ƿ� �������� ������ ��
   {
      response.sendRedirect(view);
   }

   //forward�� �̵� �� : ������ request ��ü�� ������ 
   void forward(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException // ������ ������Ʈ�� �̿��ϱ� ������ ����, ������ �����ߴ� ������ ���� 
   {
      RequestDispatcher RD = request.getRequestDispatcher(view);
      RD.forward(request, response);
   }
   void main(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
       System.out.println("main�޼ҵ� ����");
       
       ArrayList<ListMainDTOOut> dto =  (ArrayList<ListMainDTOOut>) dao.listmain("������", 4);
       
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
   	
  System.out.println("type��  "+type);
	if(type.equals("trip")==true){
		type = "������";
	}else if(type.equals("restaurant")==true){
		type = "����";
	}else if(type.equals("specialty")==true){
		type = "Ư�깰";
	}
   	
   	if(word == null || word.equals("null")==true || word.equals("")==true){   // word.equals("null")==true  �� ������� �ФФФ�
		System.out.println("���� -1�̾����");
		
		dto = new pageDTOIn(pageNo, pageSize, type);
	}else{
		System.out.println("���� 0�̾����");
	
		dto = new pageDTOIn(pageNo, pageSize, mode, word, type);
	}
	
   	ArrayList<UserDTOOut> list = (ArrayList<UserDTOOut>) dao.list(dto);
	
   	
   	/////////////////////////////////////////////////////////////////////////
   	
   	
   	int total = dao.countAll(type, dto);
   	
    int pageNum = total/pageSize;
    if(total % pageSize != 0) pageNum++; //������ ����
    
    int prevPage = pageNo-1; //���� ������
                       //prevPage�� -1�̸� ������������ ��� ����
    
    int nextPage = pageNo+1; //���� ������
    
   
    if(nextPage >= pageNum) nextPage=-1; //nextPage�� -1�̸� ���������� ��� ����
    
    //�������� ������ ��ȣ ���� ����
    if(pageNo > viewEnd-1){
    	viewStart++;
    	viewEnd++;
    }
    
    if(pageNo < viewStart){
    	viewStart--;
    	viewEnd--;
    }
    System.out.println("type��  "+type);
    //viewStart�� viewEnd�� ���� Ȯ��
    if(viewStart<0) viewStart=0;
    if(viewEnd>pageNum) viewEnd = pageNum;
    
    PageDTOOut pageOut = new PageDTOOut(   pageNo,      //���� ������ ��ȣ
                            pageSize,    //�������� ���� ����
                            total,       //��ü ���� ����
                            pageNum,   //������ ����
                            prevPage,    //���� ������ ��ȣ
                            nextPage, //���� ������ ��ȣ
                            word,
                            viewStart,viewEnd);  
    
    request.setAttribute("PAGEOUT", pageOut);
    
    /////////////////////////////////////////////////////////////////
    
//    if(type.equals("������")==true){
//		request.setAttribute("TRIP", list);
//		//forward�� �������� �̵���
//    	forward(request, response, "trip.jsp");
//	}else if(type.equals("����")==true){
//		request.setAttribute("RESTAURANT", list);
//		forward(request, response, "restaurant.jsp");
//	}else if(type.equals("Ư�깰")==true){
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
      System.out.println("user �� �о���� ��ɾ�� �� ��ȣ : "+num);
      
//      int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//      int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//      int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//      int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//    �̹� request�� ����ִ°͵�
      
      Cookie[] cookies = request.getCookies();
    
//      String ip = request.getRemoteAddr(); ip�ּ�
      
      if(cookies==null || cookies.length==0){
    	  
    	   Cookie c= new Cookie(id, clickNum);  //�ϳ��� ���̵� ��ȸ�� �۹�ȣ ����
          c.setMaxAge(60*60*6) ;  //6�ð�
          System.out.println("cookie id="+c.getName());
          response.addCookie(c) ;
      }
      
      int shit = -1;
      
      
      for(int i=0; i < cookies.length; i++){  
    	  
    	  String cName = cookies[i].getName();
    	  System.out.println("**cName="+cName);
    	  String cValue = cookies[i].getValue() ;
    	  System.out.println("**cValue="+cValue);
    	  
    	  if(cName.equals(id)==true){  //�̹� �Ƶ����� ��Ű�̸��� �����Ҷ�
    		  
    		  shit =0;
    		  
              System.out.println("***�̹� ���̵� ��Ű�̸� ����");

    		
    		  System.out.println("***cValue="+cValue);

        	  String[] values = cValue.split("and");
        	  
        	  int shit2=-1;
        	  
    		  for(int j=0; j<values.length ; j++){
    			  
    			  System.out.println("��Ű�� ����:"+values.length);
    			 System.out.println("**��Ű��:"+values[j]);
    			 System.out.println("**�Խñ�:"+clickNum);
        		  if(values[j].equals(clickNum)==true){ //��Ű���� �̹� ��ȸ�� �۳ѹ��� ������ 
        			  
        			  System.out.println("****��Ű��:"+values[j]+"�Խñ�:"+clickNum);
        		        
        			  shit2 = 0;
        			  
        			  System.out.println("�̹� ��ȸ�� ���Դϴ�");
        	          
                  }else{
                  }
        		
    		  }
    		//�۳ѹ��� ������ �ϳ��� ���ٸ�
        	  if(shit2 == -1){
        		  
        		  System.out.println("***Ŭ���� ��Ű��:"+cValue);
        		  
        		  cValue = cValue + "and"+clickNum;  //��Ű�� ���ڿ��� �ѹ��� �߰�  !!!!�ФФФФм��ð���������: ��Ű������ "," �̳� ";" ���ȵ� �Ф�
        		  
        		  System.out.println("***Ŭ���� ��Ű��:"+cValue);
        		  
        		  System.out.println(id);
        		  
        		   Cookie c = new Cookie(id, cValue);  //����

        		  System.out.println("***������ ��Ű��:"+c.getValue()+"  �̸�:"+c.getName());
        		 
        		 
    	          daoReply.click(num);//��ȸ�� ����
    	        
                  System.out.println("***��ȸ������!!!");
                  
                  response.addCookie(c);

        	  }
        	 
    	  }else{
             
    	  }
    	
      }
      
      if(shit==-1){   
    	 
    	  System.out.println("***���̵� ��Ű �̸� ����");

    	 Cookie c = new Cookie(id, clickNum);
		  c.setMaxAge(60*60*6) ;  //6�ð�
          System.out.println("cookie id="+c.getName());
         
          System.out.println("***����Ű����");
          daoReply.click(num);//��ȸ�� ����
          System.out.println("***��ȸ������");
        response.addCookie(c) ;
      }
      
     
	  
		 
      
      BoardDTOOut dto = dao.read(num); //num�� �ش��ϴ� ���� �о��
      
      ArrayList<ReplyDTOOut> list = daoReply.read(num); //num���ش��ϴ� ��� 
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
		   System.out.println("��۵�ϿϷ�: num="+num+"  content="+content+"  id="+id);
		   sendReDirect(response, "read.user?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&search="+word);
		  
	   }else{
		
	   }
	   
   }
   
   public void replyDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   PrintWriter out = response.getWriter();
	  
	   int rpNum = Integer.parseInt(request.getParameter("rpNum"));  //������ ��� ������ȣ
	  
	  
	   System.out.println("�����Ҵ�۹�ȣ:"+rpNum);
//	   
//	   int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//	     int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//	     int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//	     int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//	     String word = request.getParameter("search");
//	   
	   if(daoReply.del(rpNum)==true){
		   System.out.println("�ش� ����� �����Ǿ����ϴ�");
		   out.print("{\"ret\":true}");
		   
    	}else{
    		out.print("{\"ret\":false}");
    	}
//		   sendReDirect(response, "read.user?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&search="+word);
	   
   }
   
   public void replyMod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   int rpNum = Integer.parseInt(request.getParameter("rpNum"));  //������ ��� ������ȣ
		
	   PrintWriter out = response.getWriter();

	  
	   String content = request.getParameter("content");
	   
	   System.out.println("������ ��� ��ȣ:"+rpNum);
	   
	   if(daoReply.mod(rpNum, content)==true){
		   System.out.println("�ش� ����� �����Ǿ����ϴ�");
		   out.print("{\"ret\":true}");
		   
    	}else{
    		out.print("{\"ret\":false}");
    	}
	    	 
   }
   
   public void nice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	   
	   int num = Integer.parseInt(request.getParameter("num"));
	   String type = request.getParameter("type");
	   String id = request.getParameter("id");
	   
	   ////////////    ��Ű������ ��ȸ�� ���
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
//		  System.out.println("�̹� ��ȸ�ѱ�");
//	  }
	   
	   
	   /////////////////////////////////////////////
	
		   System.out.println("���ƿ� �� �۹�ȣ:"+num);

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
	   
	
	   System.out.println("���ƿ� ����� �۹�ȣ:"+num);

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
	  
	  response.addCookie(cookie); //��Ű�� �������� ����/����
	  
  }
  
  boolean checkCookie(String key, String value, HttpServletRequest request){
	  
	  Cookie[] cookies = request.getCookies();
	  
	  for(int i=0; i<cookies.length ;i++){
		  String cKey = cookies[i].getName();
		  String cValue = cookies[i].getValue();
		  
		  if(cKey.equals(key) ==true && cValue.equals(value)==true){
			  
			  return true;  //���ϴ� ��Ű�� ����
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
            System.out.println("���� ����");
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
//        	 System.out.println("��Ű�����");
//        	 
//        	 //1.��Ű ��ü �����ϱ�
//        	 //niceŬ�� Ȯ���� �� ���� ������ Ű ����
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
//        	 //2.��Ű�� ���˵Ǵ� �ð��� ����
//        	 c.setMaxAge(60*60*24*365);
//        	 //3.��Ű�� ��Ĺ���� �������� ������ response�� �����Ͽ� ����
//        	 
//         }else if(cmd.equals("readCookie.user")==true){
//        	 System.out.println("��Ű �о����");
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