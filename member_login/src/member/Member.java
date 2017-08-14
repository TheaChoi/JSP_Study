package member;



import java.sql.SQLException;
import java.util.Scanner;

public class Member {

   public static void main(String[] args) {
     
      Session session = new Session(); //아이디 저장
      Scanner scan=new Scanner(System.in);
    
   for(;;){   
      try{
         
           
            DAO dao     =new DAO();
            
            
            // 메뉴] 1.회원가입 2.로그인 3.회원가입 4.개인정보수정 5.회원탈퇴 
            System.out.println("메뉴] 1.회원가입 2.로그인 3.로그아웃 4.개인정보수정 5.회원탈퇴  6.비밀번호변경");
            
            int menu =scan.nextInt();
            
            if(menu==1)  //회원가입 
            {
               System.out.println("-------회원가입--------");
               
               String id;
               String pw;
               String name;
               
               System.out.print("아이디 :");
               id = scan.next();
               System.out.print("비밀번호 :");
               pw = scan.next();
               System.out.print("이름 :");
               name = scan.next();
               
               //회원가입 메소드를 호출
               
               if(dao.reg(id, pw, name) == true)
               {
                  System.out.println("회원가입을 성공했습니다.");
               }
            }if(menu==2)  //로그인
            {
               System.out.println("-------로그인--------");
               if(session.getAttribute("UserID")==null)
               {
               String id;
               String pw;
               
               System.out.print("아이디 :");
               id = scan.next();
               System.out.print("비밀번호 :");
               pw = scan.next();
               
               if(dao.login(id, pw) == true)
               {//로그인됨
                  session.setAttribute("UserID", id);
                  dao.inc(id);
                  
                  String userID = session.getAttribute("UserID");
                  
                  System.out.println(userID+"님 환영합니다.");
               } else
               {
                  System.out.println("아이디 또는 비밀번호가 틀림");
               }
               
               }
               else{
                  System.out.println("이미 로그인 되어 있음");
               }
            }else if(menu==3){   //로그 아웃
                
                //이미 로그인이 된 상태
                if(session.getAttribute("UserID") != null)
                {
                   //로그 아웃 상태로 만듬.
                   //session 객체에 저장된 로그인 정보를 제거함
                   session.removeAttribute("UserID");
                   
                   System.out.println("로그 아웃 하였습니다.");
                }else
                {
                   System.out.println("로그인 되지 않음");
                }
             }
            else if(menu==4){ //회원정보 수정
            	System.out.println("------회원정보수정---------");
            	
            	//1. 로그인을 확인함
            	if(session.getAttribute("UserID") !=  null) {
            		
            		InfoData data;
            		
            		String id = session.getAttribute("UserID");
            		
            		//2. 로그인 아이디에 대한 정보를 가져옴
            		data = dao.getInfo(id);
            		
            		if(data != null) {
            			
            			//3. 기존 정보를 출력하고 일부 내용을 편집함
            			
            			System.out.println("--------이전 정보-------");
            			System.out.println("기존 pw : "+data.pw);
            			System.out.println("기존 이름 : "+data.name);
            			
            			System.out.println("--------새로운 정보-------");
            			String pw, name;
            			System.out.print("새로운 비밀번호를 입력하시오 : ");
            			pw = scan.next();
            			System.out.print("새로운 이름를 입력하시오 : ");
            			name = scan.next();
            		
            			//4.변경된 내용을 데이타베이스에 저장함
            			InfoData Indata=new InfoData();
            			
            			Indata.pw = pw;
            			Indata.name =name;
            			
            			if(dao.setInfo(id, Indata)==true) {
            				System.out.println("개인정보가 성공적으로 변경되었습니다");
            			}
            			
            		}
            		
            	}else{
            		System.out.println("아직 로그인 되지 않음");
            		
            	}
            	
            }
            else if(menu==5) //회원탈퇴 
             {
            	 System.out.println("---------회원탈퇴-----------");
            	 
            	 
            	 //이미 로그인 상태
            	 if(session.getAttribute("UserID") != null) {
            		 
            		 String id = session.getAttribute("UserID");
            		 System.out.println("회원 탈퇴 아이디 :"+id);
            		 dao.del(id);
            		 
            	 }else {
            		 System.out.println("아직 로그인 되지 않음");
            	 }
             }else if(menu==6) {//비밀번호변경
            	 
            	 System.out.println("---------비밀번호 변경---------");
            	 
            	 if(session.getAttribute("UserID")!=null) {
            		 
            		 String id = session.getAttribute("UserID");
            		 
            		 String pw1; //기존 비밀번호 입력 저장
            		 String pw2; //새로운 비밀번호 입력 저장
            		 
            		 System.out.print("기존 비밀번호를 입력하시오 : ");
            		 pw1 = scan.next();
            		 System.out.print("새로운 비밀번호를 입력하시오 : ");
            		 pw2 = scan.next();
            		 
            		if(dao.chgPassword(id, pw1, pw2)==true ) {
            			System.out.println("비밀번호가 성공적으로 변경되었습니다");
            		}else{
            			System.out.println("비밀번호를 변경하지 못했습니다");
            		}
            		 
            	 }else{
            		 System.out.println("아직 로그인 되지 않음");
            	 }
            	 
            	 
            	 
             }

           
            
         }catch(ClassNotFoundException e)
         {
            System.out.println("커넥터 에러 : "+e.getMessage());
         }catch (SQLException e) {
            
            System.out.println("SQL 에러 : "+e.getMessage());
         }   
      }//end of for(;;)
 
   }
   
   
}