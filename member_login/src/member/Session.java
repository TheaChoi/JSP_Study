package member;

public class Session {

	private String name;
	private String value;
	
	void setAttribute(String name, String value) {
		
		this.name = name;
		this.value = value;
	}
	
	 String getAttribute(String name)
	   {
	      
	      if(this.name != null && this.name.equals(name) == true )
	      {
	         return value;
	      }else{
	         return null;
	      }
	   }
	 
	 void removeAttribute(String name) {
		 
		  if(this.name != null && this.name.equals(name) == true)
	      {
	         this.name = null;
	         this.value = null;
		 }
	 }
	 
	 
}
