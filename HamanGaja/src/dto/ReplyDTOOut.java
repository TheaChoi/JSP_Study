package dto;

public class ReplyDTOOut {
	
	private int num, link;
	private String content, id, reg_date;
	
	public ReplyDTOOut(int num, int link, String content, String id, String reg_date) {
		super();
		this.num = num;
		this.link = link;
		this.content = content;
		this.id = id;
		this.reg_date = reg_date;
	}
	
	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLink() {
		return link;
	}
	public void setLink(int link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	

}
