package board;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class BoardDAO {

	private SqlSessionFactory factory;
	
	public BoardDAO() throws IOException {
		
		String xml = "mybatis-config.xml";
		//1.myBatis 구성 파일 (configuration xml)을 읽어옴
		Reader reader = Resources.getResourceAsReader(xml);
		//2.Factory 객체를 생성함
		factory = new SqlSessionFactoryBuilder().build(reader);
	}
	
	boolean write(BoardDTOIn dto) {
		
		int ret;
		
		//1.sql session 객체 생성
		SqlSession session = factory.openSession(true);
		
		//2.sql 질의문 실행./결과 반환
		ret = session.insert("board.insertBoard", dto);
//		<mapper namespace="board"><!--namespace의 아이디를 통해 들어온다-->
//			<insert id="insertBoard" parameterType="BoardDTOIn">  <!-- 태그를 실행시킬 때 BoardDTOIn 객체의 데이타를 넘겨줌 -->
//				insert into board set title=#{title}, #{content}, count=0, reg_date=now();
//			</insert>
//		</mapper>

		//3. sql session 닫음
				session.close();
				
		if(ret==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	List list() {  //전체 글목록을 가져옴z
		
		SqlSession session = factory.openSession(true);
		
		List li= session.selectList("board.selectList");
		
		session.close();
		
		return li;
		 
		/********************
		System.out.println("리스트의 목록 개수 : "+li.size());
		
		for(int i=0 ; i<li.size() ;i++) {
			
			HashMap hashmap = (HashMap)li.get(i); //list에서 i번째 hashmap 
			
			int num = (int)hashmap.get("num");
			String title = (String)hashmap.get("title");
			int count = (int)hashmap.get("count");
			Timestamp reg_date = (Timestamp)hashmap.get("reg_date");
			
			System.out.println("num:"+num+"  title:"+title+"  count:"+count+"  reg_date:"+reg_date);
		}
		********************************/
	}
	
	//pageNo:가져올 페이지 번호, 0부터 시작
	//articleCnt: 페이제에 가져올 글 목록 개수
	List list(int pageNo, int pageSize) {
		
		int start = pageNo * pageSize;//시작글의 위치
		PageIn in = new PageIn(start, pageSize);
		SqlSession session = factory.openSession(true);
		List li = session.selectList("board.selectPage", in);
		
		session.close();
		return li;
		
	}
	
	long count() {
		
		SqlSession session = factory.openSession(true);
		List li = session.selectList("board.selectCount");
		session.close();
		
		HashMap hashMap = (HashMap)li.get(0);
		long cnt = (long)hashMap.get("count(*)");
		
		return cnt;
	}
	
	long count(String word, int mode){
		
		SqlSession session = factory.openSession(true);
		PageIn in = new PageIn(word, mode);
		List li = session.selectList("board.selectCountFind", in);
		session.close();
		
		HashMap hashMap = (HashMap)li.get(0);
		long cnt = (long)hashMap.get("count(*)");
		
		return cnt;
	}
	
	ContentDTOOut read(int num) {//num에 해당하는 글 읽어오기
		
		SqlSession session = factory.openSession(true);
		
		ContentDTOOut dto = session.selectOne("board.selectContent",num);
		
//		HashMap hashMap = (HashMap)session.selectOne("board.selectContent",num); //하나만 존재할때
//		
//		String title = (String) hashMap.get("title");
//		String content = (String)hashMap.get("content");
//		int count = (int)hashMap.get("count");
//		Timestamp reg_date = (Timestamp) hashMap.get("reg_date");
		
		//ContentDTOOut dto = new ContentDTOOut(num, title, content, count, reg_date);
		
		session.close();
		return dto;
//		System.out.println("글번호:"+num);
//		System.out.println("글제목:"+title);
//		System.out.println("조회수:"+count);
//		System.out.println("작성일:"+reg_date);
		
		
		//   <select id="selectContent" parameterType="int" resultType="hashmap">
		//select num, title, content, count, reg_date from board where num=#{content};
		
	}
	
	void del(int num) {
		
		SqlSession session = factory.openSession(true);
		session.delete("board.deleteContent", num);
		
		session.close();
	}
	
	List find(String word, int mode, int pageNo, int pageSize){
		SqlSession session = factory.openSession(true);
		
		int start = pageNo * pageSize;
		
		PageIn in = new PageIn(word, mode, start, pageSize);
		List li = session.selectList("board.selectPageFind", in);
		
//		for(int i=0; i<li.size() ; i++){
//			
//			HashMap hashMap = (HashMap)li.get(i);
//			
//			String title=(String)hashMap.get("title");
//			
//		}
//		
		session.close();
		
		return li;
	}
}

