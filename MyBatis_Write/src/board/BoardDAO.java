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
		//1.myBatis ���� ���� (configuration xml)�� �о��
		Reader reader = Resources.getResourceAsReader(xml);
		//2.Factory ��ü�� ������
		factory = new SqlSessionFactoryBuilder().build(reader);
	}
	
	boolean write(BoardDTOIn dto) {
		
		int ret;
		
		//1.sql session ��ü ����
		SqlSession session = factory.openSession(true);
		
		//2.sql ���ǹ� ����./��� ��ȯ
		ret = session.insert("board.insertBoard", dto);
//		<mapper namespace="board"><!--namespace�� ���̵� ���� ���´�-->
//			<insert id="insertBoard" parameterType="BoardDTOIn">  <!-- �±׸� �����ų �� BoardDTOIn ��ü�� ����Ÿ�� �Ѱ��� -->
//				insert into board set title=#{title}, #{content}, count=0, reg_date=now();
//			</insert>
//		</mapper>

		//3. sql session ����
				session.close();
				
		if(ret==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	List list() {  //��ü �۸���� ������z
		
		SqlSession session = factory.openSession(true);
		
		List li= session.selectList("board.selectList");
		
		session.close();
		
		return li;
		 
		/********************
		System.out.println("����Ʈ�� ��� ���� : "+li.size());
		
		for(int i=0 ; i<li.size() ;i++) {
			
			HashMap hashmap = (HashMap)li.get(i); //list���� i��° hashmap 
			
			int num = (int)hashmap.get("num");
			String title = (String)hashmap.get("title");
			int count = (int)hashmap.get("count");
			Timestamp reg_date = (Timestamp)hashmap.get("reg_date");
			
			System.out.println("num:"+num+"  title:"+title+"  count:"+count+"  reg_date:"+reg_date);
		}
		********************************/
	}
	
	//pageNo:������ ������ ��ȣ, 0���� ����
	//articleCnt: �������� ������ �� ��� ����
	List list(int pageNo, int pageSize) {
		
		int start = pageNo * pageSize;//���۱��� ��ġ
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
	
	ContentDTOOut read(int num) {//num�� �ش��ϴ� �� �о����
		
		SqlSession session = factory.openSession(true);
		
		ContentDTOOut dto = session.selectOne("board.selectContent",num);
		
//		HashMap hashMap = (HashMap)session.selectOne("board.selectContent",num); //�ϳ��� �����Ҷ�
//		
//		String title = (String) hashMap.get("title");
//		String content = (String)hashMap.get("content");
//		int count = (int)hashMap.get("count");
//		Timestamp reg_date = (Timestamp) hashMap.get("reg_date");
		
		//ContentDTOOut dto = new ContentDTOOut(num, title, content, count, reg_date);
		
		session.close();
		return dto;
//		System.out.println("�۹�ȣ:"+num);
//		System.out.println("������:"+title);
//		System.out.println("��ȸ��:"+count);
//		System.out.println("�ۼ���:"+reg_date);
		
		
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

