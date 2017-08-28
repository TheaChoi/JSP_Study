package dto;

public class BoardDTOIn 
{
   private String type; //글의 종류 (관광지, 맛집, 특산물)
   private String title;
   private String content;
   private String photo;
   
   
   
   public BoardDTOIn(String type, String title, String content, String photo) {
      super();
      this.type = type;
      this.title = title;
      this.content = content;
      this.photo = photo;
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