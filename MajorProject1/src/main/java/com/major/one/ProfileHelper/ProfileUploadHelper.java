package com.major.one.ProfileHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ProfileUploadHelper {
	private final String UPLOAD_DIR = "C:\\Users\\Shivam\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\MajorProject1\\src\\main\\resources\\static\\profileImages";

//	private final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	public ProfileUploadHelper() throws IOException {}
	
	public boolean uploadFile(MultipartFile f) {
		
		boolean res=false;
		
		try {
			
			InputStream is=f.getInputStream();
			byte data[]=new byte[is.available()];
			is.read(data);
			//write
			
			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+f.getOriginalFilename());
			fos.write(data);
			
			fos.flush();
			fos.close();
			res=true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
