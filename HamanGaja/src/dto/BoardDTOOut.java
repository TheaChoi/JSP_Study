package dto;

public class BoardDTOOut {

	private int num, nice, count;
	private String type, title, content, photo, map, reg_date;
	
	
	public BoardDTOOut(int num, int nice, int count, String type, String title, String content, String photo,
			String map, String reg_date) {
		super();
		this.num = num;
		this.nice = nice;
		this.count = count;
		this.type = type;
		this.title = title;
		this.content = content;
		this.photo = photo;
		this.map = map;
		this.reg_date = reg_date;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getNice() {
		return nice;
	}


	public void setNice(int nice) {
		this.nice = nice;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getMap() {
		return map;
	}


	public void setMap(String map) {
		this.map = map;
	}


	public String getReg_date() {
		return reg_date;
	}


	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
