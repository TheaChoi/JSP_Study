package upload;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class Upload {
	
	private String savePath;
	private int maxSize;
	private String encType;
	private String orgName;   //���Ͼ��ε� �Ŀ� �ٸ� ���Ͽ��� 
	private String saveName;   //�����˰������ getter�� �ҷ�����

	public Upload(String savePath, int maxSize, String encType) {
		
		this.savePath = savePath;
		this.maxSize = maxSize;
		this.encType = encType;
	}
	
	public String getSavePath() {
		return savePath;
	}

	public String getSaveName() {
		return saveName;
	}

	public void UploadFile(HttpServletRequest request) throws IOException {
		
		FileRenamePolicy rename = new DefaultFileRenamePolicy();
		
		MultipartRequest MR = new MultipartRequest(request, savePath, maxSize, encType, rename);
		
		
		orgName=MR.getOriginalFileName("file");
		saveName=MR.getFilesystemName("file");
		
		System.out.println("���ε� ���� �̸� : "+orgName);
		System.out.println("����� ���� �̸� : "+saveName);
	}
}
