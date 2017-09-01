package control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import dto.BoardDTOOut;
import dto.BoardModDTOOut;
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
    	
    	String path=request.getSession().getServletContext().getRealPath("/")+"/upload";
    	//파일 저장 장소
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
        System.out.println("파일이름:"+filename);
        //DTO를 DAO 전달해줌
        type = URLEncoder.encode(type, "UTF-8") ;
        if(dao.write(dto)==true){
        	sendReDirect(response, "List.board?pageNo=0&pageSize=5&viewStart=0&viewEnd=5&type="+type);
        }
    }
    
    public void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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
    
    void read(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
       
       int num = Integer.parseInt(request.getParameter("num"));
       System.out.println("글 읽어오기 명령어와 글 번호 : "+num);
       
//       int pageNo = Integer.parseInt(request.getParameter("pageNo"));
//       int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//       int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
//       int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
//     이미 request에 담겨있는것들
       
       BoardDTOOut dto = dao.read(num); //num에 해당하는 글을 읽어옴
       
       request.setAttribute("BOARDDTOOUT", dto);
       forward(request, response, "/admin/view.jsp");
    }
    
    void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	
    	   int pageNo = Integer.parseInt(request.getParameter("pageNo"));
           int pageSize = Integer.parseInt(request.getParameter("pageSize"));
           int viewStart =  Integer.parseInt(request.getParameter("viewStart"));
           int viewEnd =  Integer.parseInt(request.getParameter("viewEnd"));
           String word = request.getParameter("search");
    	
    	int num = Integer.parseInt(request.getParameter("num"));
    	String type = request.getParameter("type");
    	//System.out.println("num="+num+"  type="+type);
    	String photo = request.getParameter("photo");
    	String path=request.getSession().getServletContext().getRealPath("/")+"/upload";
    	String filepath = path+"/"+photo;
    	
    	File file = new File(filepath);
    	
    	if(file.exists()==true){
    		file.delete();
    		
    	}
    	
    	type = URLEncoder.encode(type, "UTF-8");   //encode해서 넘겨줬지만 다시 list에 넘겨주므라 다시 encoding하기!!
    	if(dao.delete(num)==true){
    		System.out.println(num+"번 글이 삭제되었습니다.");
    		sendReDirect(response, "deleteSuccess.jsp?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&type="+type+"&search="+word+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&type="+type);
    	}else{
    		System.out.println("글삭제에 실패하였습니다");
    	}
    }
    
    void contents(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int num = Integer.parseInt(request.getParameter("num"));
    	BoardModDTOOut dto = dao.contents(num);
    	request.setAttribute("BOARDMODDTOOUT", dto);
    	forward(request, response, "/admin/modBoard.jsp?num="+num);
    }
    
    public void mod(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
    	
    
    	
    	//int num = Integer.parseInt(request.getParameter("modNum"));   MultipartRequest
    	//System.out.println(num);
    	
    	String path=request.getSession().getServletContext().getRealPath("/")+"/upload";
    	//파일 저장 장소
    	int size=1024*1024*100;//파일크기 100메가
    	String encType="UTF-8";
    	
    	MultipartRequest mr = new MultipartRequest(request, path, size, encType,new DefaultFileRenamePolicy());//이름중복방지
    	
    	//기타입력정보
    	int num = Integer.parseInt(mr.getParameter("num"));    //getParameter("modNum")이면 read()에 forward로 넘길떄 곤란하다
    	System.out.println("글번호:"+num);
    	String type=mr.getParameter("type"); //관광지, 맛집, 특산물
    	String title= mr.getParameter("title");
    	String content=mr.getParameter("content");
    	int fc = Integer.parseInt(mr.getParameter("fc"));
    	
    	String prevPhoto = mr.getParameter("prevPhoto");
    	String filename = mr.getFilesystemName("pic"); //저장파일이름
    	System.out.println("사진바뀜여부:"+fc+"  /  "+prevPhoto);
    	
    	int pageNo = Integer.parseInt(mr.getParameter("pageNo"));   //multipartRequest는 request.getParameter 로 가져올수 없다
        int pageSize = Integer.parseInt(mr.getParameter("pageSize"));
        int viewStart =  Integer.parseInt(mr.getParameter("viewStart"));
        int viewEnd =  Integer.parseInt(mr.getParameter("viewEnd"));
        String word = "";  //mr.getParameter("search");null이 아니어야 먹히네

    	System.out.println("!!pageno="+pageNo);
    	System.out.println("11pageSize="+pageSize);
    	System.out.println("!!viewStart="+viewStart);
    	System.out.println("!!viewEnd="+viewEnd);
    	System.out.println("!word="+word);
    	
    	//DTO 객체를 생성함
        BoardDTOIn dto = new BoardDTOIn(type, title, content, filename);
        System.out.println("파일이름:"+filename);
        //DTO를 DAO 전달해줌
        type = URLEncoder.encode(type, "UTF-8") ;
        
        
        
        if(fc==0){
	        if(dao.mod(dto, num)==true){
	        	System.out.println("글 수정이 완료되었습니다.");  //mr로 넘겨준 것들이니 forward로 넘겨줘도 read.board에서 쓸수 없다, request에 담겨있어야함.
	        	forward(request, response, "read.board?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&search="+word+"&type="+type+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&type="+type);
	        }
        }else{
        	if(dao.mod2(dto, num)==true){
	        	System.out.println("글 수정이 완료되었습니다.");
	        	sendReDirect(response, "read.board?num="+num+"&pageNo="+pageNo+"&pageSize="+pageSize+"&search="+word+"&type="+type+"&viewStart="+viewStart+"&viewEnd="+viewEnd+"&type="+type);
	        }
        }
    }
    
    public void delPhoto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	
    	System.out.println("들어왔다");
    	
    	//int num = Integer.parseInt(request.getParameter("num"));
    	
    	System.out.println("삭제할 사진 글 번호; "+request.getParameter("num"));
    	
    	//dao.delPhoto(num);
    	
    	PrintWriter out = response.getWriter();
    
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String cmd = parseCommand(request);
		System.out.println("cmd="+cmd);
		try{	
			if(cmd.equals("write.board")==true){
				write(request, response);
			}else if(cmd.equals("List.board")==true){
				list(request,response);
			}else if(cmd.equals("read.board")==true){
				read(request, response);
			}else if(cmd.equals("delete.board")==true){
				delete(request, response);
			}else if(cmd.equals("contents.board")==true){
				contents(request, response);
			}else if(cmd.equals("mod.board")==true){
				mod(request, response);
			}else if(cmd.equals("delPhoto.board")==true){
				delPhoto(request, response);
			}
		}
			catch(SQLException e){
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
