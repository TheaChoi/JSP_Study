package board;

import java.sql.Timestamp;

public class ContentDTOOut {

	private int num;
	private String title;
	private String content;
	private int count;
	private Timestamp reg_date;
	
	public ContentDTOOut(){ //기본생성자 : 기본이외의 생성자가 있으면 사라지기 때문에 만들어줘야함.
		
	};
	
	public ContentDTOOut(int num, String title, String content, int count, Timestamp reg_date) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.count = count;
		this.reg_date = reg_date;
	}
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}


}
