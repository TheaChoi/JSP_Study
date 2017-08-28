package dto;

public class ModDTOIn {
	private String id; //정보를 수정할 회원의 아이디
	private String pw, phone, email; //새로운 정보들
	
	public ModDTOIn(String id, String pw, String phone, String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
