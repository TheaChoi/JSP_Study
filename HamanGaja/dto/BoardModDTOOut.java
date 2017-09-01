package dto;

public class BoardModDTOOut {
	
	private String type, title, content, photo, map;

	public BoardModDTOOut(String type, String title, String content, String photo, String map) {
		super();
		this.title = title;
		this.type = type;
		this.content = content;
		this.photo = photo;
		this.map = map;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
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
	

}
