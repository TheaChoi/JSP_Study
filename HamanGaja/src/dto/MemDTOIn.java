package dto;

//����� �Է��� DAO�� �����ϱ� ���� ��ü
public class MemDTOIn {

	private String id, pw, phone, email, address;

	public MemDTOIn(String id, String pw, String phone, String email, String address) {
		super();
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
