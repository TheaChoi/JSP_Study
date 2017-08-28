package dto;

public class infoDTOOut {
	
		private String tel1, tel2, tel3; //출력형식
	   private String emailid, emailser;
	   
	   
	   public infoDTOOut(String phone, String emailAddr)
	   {
	      //phone 010-111-1234
	      String[] tels = phone.split("-"); //010,111,1234
	      
	      this.tel1 = tels[0];
	      this.tel2 = tels[1];
	      this.tel3 = tels[2];
	      
	      String[] emails = emailAddr.split("@"); //shc, naver.com
	      this.emailid = emails[0];
	      
	      this.emailser = emails[1];
	      
	   }

	   public String getTel1() {
	      return tel1;
	   }

	   public void setTel1(String tel1) {
	      this.tel1 = tel1;
	   }

	   public String getTel2() {
	      return tel2;
	   }

	   public void setTel2(String tel2) {
	      this.tel2 = tel2;
	   }

	   public String getTel3() {
	      return tel3;
	   }

	   public void setTel3(String tel3) {
	      this.tel3 = tel3;
	   }

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmailser() {
		return emailser;
	}

	public void setEmailser(String emailser) {
		this.emailser = emailser;
	}

	   
}
