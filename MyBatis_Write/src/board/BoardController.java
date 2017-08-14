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
       //(1)�׽�Ʈ�� DTO ��ü ����
      String title = request.getParameter("title");
      String content = request.getParameter("content");
            
      System.out.println("���� : "+title+"���� : "+content);      // �۾��� �������� Ȯ���� request.setCharacterEncoding("UTF-8"); ��ֱ� (������)
         
      BoardDTOIn dto = new BoardDTOIn(title,content);
      if(dao.write(dto)==true) {
    	  //���������� ���� �����ͺ��̽��� �����
    	  //�۸�ϰ����ͼ� ����� jsp view���� ����
    	  
    	  //(1)�ٷ� list �޼ҵ带 ���� ȣ���Ѵ� (���ΰ�ħ�ϸ�۾�����ͽ���)
    	  //list(request,response);
    	  
    	  //(2) response�� sendRedirect�޼ҵ� �̿�,  ���ΰ�ħ�ص� �۾������ �������� �ʴ� ���!
    	  response.sendRedirect("list.bo");
    	  
    	  //(3) requestDispatcher �� �̿� (���ΰ�ħ�ϸ�۾�����ͽ���)
    	 // RequestDispatcher RD = request.getRequestDispatcher("list.bo?pageNo=0&pageSize=5");
    	 // RD.forward(request, response);
    	  
    	  
      }else{//�����ͺ��̽��� ���� ������� ����
    	  
      }
    }
    
    void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//List list = dao.list();
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	
    	List list = dao.list(pageNo, pageSize);
    	
    	int total = (int)dao.count();  //���� �Ѱ���
    	int pageNum = total/pageSize;
    	if(total%pageSize != 0) pageNum++; //������ ����
    	
    	int prevPage = pageNo-1; //���� ������ ,  ������������ -1�̸� ���������� ��� ����
    	int nextPage = pageNo+1;//���� ������
    	if(nextPage >= pageNum) nextPage=-1; //nextPage�� -1�̸� ������������� ����
    	
    	PageOut pageOut = new PageOut(pageNo, pageSize, total, pageNum, prevPage, nextPage);
    	
    	request.setAttribute("PAGEOUT", pageOut);
    	
    	request.setAttribute("LIST", list);
    	
    	RequestDispatcher RD = request.getRequestDispatcher("list.jsp");
    	RD.forward(request, response);
    }
    
    void read(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int num = Integer.parseInt(request.getParameter("num")); //getParameter�� �о���� �� ��� String�̴�
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	
    	System.out.println("�۹�ȣ:"+num+"  ��������ȣ:"+pageNo+"   �������۰���:"+pageSize);
    	
    	ContentDTOOut dto = dao.read(num);
    	
    	request.setAttribute("CONTENTDTOOUT", dto);
    	
    	RequestDispatcher RD = request.getRequestDispatcher("readOne.jsp");
    	RD.forward(request, response);
    }
    
    void del(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	int num = Integer.parseInt(request.getParameter("num"));
    	
    	System.out.println(num+"�� �� �����ϱ�");
    	dao.del(num);  //�� �����ߴ�
    	response.sendRedirect("list.bo?pageNo=0&pageSize=5");
    }
      
    void find(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	
    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
    	String word = request.getParameter("word");//�˻���
    	int mode = Integer.parseInt(request.getParameter("mode"));//�˻����
    	
    	System.out.println("�˻���: "+word);
    	List list = dao.find(word, mode, pageNo, pageSize);
    	request.setAttribute("LIST", list);
    	///////////////////////////////////////////
    	int total = (int)dao.count(word, mode);  //���� �Ѱ���
    	int pageNum = total/pageSize;
    	if(total%pageSize != 0) pageNum++; //������ ����
    	
    	int prevPage = pageNo-1; //���� ������ ,  ������������ -1�̸� ���������� ��� ����
    	int nextPage = pageNo+1;//���� ������
    	if(nextPage >= pageNum) nextPage=-1; //nextPage�� -1�̸� ������������� ����
    	
    	PageOut pageOut = new PageOut(pageNo, pageSize, total, pageNum, prevPage, nextPage);
    	
    	request.setAttribute("PAGEOUT", pageOut);
    	
    	
    	
    	RequestDispatcher RD = request.getRequestDispatcher("list.jsp");
    	RD.forward(request, response);
  
    	
    }
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String cmd = parseCommand(request);
		System.out.println("���� ���� cmd= "+cmd);
		
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
