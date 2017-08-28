package dto;

public class PageDTOOut
{
   private int pageNo;      //현재 페이지
   private int pageSize;   //페이지의 글의 개수
   private int total;       //총 글의 개수
   private int pageNum;    //페이지개수
   private int prevPage;   //이전페이지 번호 -1이면 없음
   private int nextPage;    //다음 페이지 번호 -1이면 없음
   private String word;
   private int viewStart; //보여지는 글 번호의 시작
   private int viewEnd; //보여지는 글 번호의 끝
   
   public PageDTOOut(int pageNo, int pageSize, int total, int pageNum, int prevPage, int nextPage, String word, int viewStart, int viewEnd) 
   {
      super(); //?? object 클래스 = 자바의 모든 클래스의 처음 부모 클래스 
      this.pageNo = pageNo;
      this.pageSize = pageSize;
      this.total = total;
      this.pageNum = pageNum;
      this.prevPage = prevPage;
      this.nextPage = nextPage;
      if(word==null){
    	  this.word="";  //검색어 없는 경우 빈 문자열을 넣어줌
      }else{
    	  
          this.word = word;
      }
      
      this.viewStart = viewStart;
      this.viewEnd = viewEnd;
    	
   }

   public int getViewStart() {
	return viewStart;
}

public void setViewStart(int viewStart) {
	this.viewStart = viewStart;
}

public int getViewEnd() {
	return viewEnd;
}

public void setViewEnd(int viewEnd) {
	this.viewEnd = viewEnd;
}

public String getWord() {
	return word;
}

public void setWord(String word) {
	this.word = word;
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

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getPageNum() {
      return pageNum;
   }

   public void setPageNum(int pageNum) {
      this.pageNum = pageNum;
   }

   public int getPrevPage() {
      return prevPage;
   }

   public void setPrevPage(int prevPage) {
      this.prevPage = prevPage;
   }

   public int getNextPage() {
      return nextPage;
   }

   public void setNextPage(int nextPage) {
      this.nextPage = nextPage;
   }

}
   
