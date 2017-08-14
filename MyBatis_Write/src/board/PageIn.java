package board;

public class PageIn {
	
	private int start;
	private int pageSize;
	
	private String word;
	private int mode;
	
	public PageIn(int start,int pageSize){
		this.start=start;
		this.pageSize=pageSize;
	}
	
	public PageIn(String word, int mode){
		this.word = "%"+word+"%";
		this.mode = mode;
	}
	
	public PageIn(String word, int mode, int start, int pageSize){
		this.word = "%"+word+"%";
		this.mode = mode;
		this.start=start;
		this.pageSize=pageSize;
	}
	
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

