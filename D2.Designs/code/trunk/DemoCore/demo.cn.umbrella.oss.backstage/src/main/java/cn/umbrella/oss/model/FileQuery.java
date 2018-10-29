package cn.umbrella.oss.model;

import org.springframework.web.multipart.MultipartFile;

public class FileQuery {
	
	private MultipartFile[] upfile;

	public MultipartFile[] getUpfile() {
		return upfile;
	}

	public void setUpfile(MultipartFile[] upfile) {
		this.upfile = upfile;
	}
	
}
