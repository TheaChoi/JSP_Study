package dto;

public class ReplyDTOIn {
	
	private int link; 
	private String content;
	private String id;
	
	public ReplyDTOIn(int link, String content, String id) {
		super();
		this.link = link;
		this.content = content;
		this.id = id;
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
