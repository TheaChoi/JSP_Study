package dto;

public class ModDTOIn {
	private String id; //������ ������ ȸ���� ���̵�
	private String pw, phone, email; //���ο� ������
	
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
