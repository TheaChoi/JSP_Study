package dto;

public class UserDTOOut {
	
	private int num,count;
	private String title, reg_date, photo, type, content;
	public UserDTOOut(int num, int count, String title, String content,String reg_date, String photo, String type) {
		super();
		this.num = num;
		this.count = count;
		this.title = title;
		this.reg_date = reg_date;
		this.photo = photo;
		this.type = type;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
