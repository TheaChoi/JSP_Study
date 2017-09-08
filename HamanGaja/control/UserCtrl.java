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
   	String word = request.getParameter("search");
   	String type = request.getParameter("type");
   	int mode = 0;
   	
  System.out.println("type��  "+type);
	if(type.equals("trip")==true){
		type = "������";
	}else if(type.equals("restaurant")==true){
		type = "����";
	}else if(type.equals("specialty")==true){
		type = "Ư�깰";
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
      System.out.println("user �� �о���� ��ɾ�� �� ��ȣ : "+num);
      
//      int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//      int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//      int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//      int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//    �̹� request�� ����ִ°͵�
      
      BoardDTOOut dto = dao.read(num); //num�� �ش��ϴ� ���� �о��
      
      request.setAttribute("BOARDDTOOUT", dto);
      forward(request, response, "userView.jsp");
   }
   
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String cmd = parseCommand(request);
   
      try{
         if(cmd.equals("main.user")==true)
         {
            main(request,response);
            System.out.println("���� ����");
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