package dto;

public class ListMainDTOOut {

	private int num;
	private String title, photo, content;
	
	
	public ListMainDTOOut(int num, String title, String photo, String content) {
		super();
		this.num = num;
		this.title = title;
		this.photo = photo;
		this.content = content;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
