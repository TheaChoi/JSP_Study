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
    	//request ��ü���� uri ��θ� ������
    	String uri = request.getRequestURI();
    	
    	String cmd = uri.substring(uri.lastIndexOf('/')+1);
    	//System.out.println("uri: "+uri);

    	return cmd;
    }
    
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
    
    void write(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    	
    	String path="D:/upload"; //���� ���� ���
    	int size=1024*1024*100;//����ũ�� 100�ް�
    	String encType="UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, size, encType,new DefaultFileRenamePolicy());//�̸��ߺ�����
    	
    	//��Ÿ�Է�����
    	String type=mr.getParameter("type"); //������, ����, Ư�깰
    	String title= mr.getParameter("title");
    	String content=mr.getParameter("content");
    	String filename = mr.getFilesystemName("pic"); //���������̸�
    	//System.out.println(type+title+content+filename);
    	
    	//DTO ��ü�� ������
        BoardDTOIn dto = new BoardDTOIn(type, title, content, filename);
        //DTO�� DAO ��������
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
    	type = URLDecoder.decode(type, "UTF-8") ;//get�Ķ���Ϳ� �ѱ��� ������
    	
    	int mode = 0;
    	String word = request.getParameter("search");
    	
    	System.out.println("search: "+word);

    	
    	System.out.println("type: "+type);
    	
    	System.out.println("��������ȣ:"+pageNo+"  �������� �۰���:"+pageSize);
    	
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
    	
    	//list�� jsp�������� �����ϱ� ���ؼ�..request��ü�� ������
    	if(type.equals("������")==true){
    		request.setAttribute("TRIP", list);
    		//forward�� �������� �̵���
        	forward(request, response, "tripList.jsp");
    	}else if(type.equals("����")==true){
    		request.setAttribute("RESTAURANT", list);
    		forward(request, response, "restaurantList.jsp");
    	}else if(type.equals("Ư�깰")==true){
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
