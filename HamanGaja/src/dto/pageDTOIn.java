package dto;

public class pageDTOIn {
	
	int pageNo, pageSize; //��� ������ ��ȣ, �������� �� ����
	int mode; //�˻����
	
	String word, type;  //�˻���, �˻�����������
	
	public pageDTOIn(int pageNo, int pageSize, int mode, String word, String type) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.mode = mode;
		this.word = word;
		this.type = type;
	}
	
	public pageDTOIn(int pageNo, int pageSize, String type) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.mode = -1;
		this.word = null;
		this.type = type;
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
