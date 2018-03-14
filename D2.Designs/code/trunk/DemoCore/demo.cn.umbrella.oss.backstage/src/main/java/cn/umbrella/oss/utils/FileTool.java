package cn.umbrella.oss.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.umbrella.oss.model.Extras;
import cn.umbrella.oss.model.SysAttachment;

import com.baidu.ueditor.define.FileType;


public class FileTool {
	
	public static SysAttachment saveFile(MultipartFile file,Extras extras, String savePath) {
		String originFileName = file.getOriginalFilename();
		String suffix = FileType.getSuffixByFilename(originFileName);
		String id = UUID.randomUUID().toString();
		SysAttachment sysAttachment = new SysAttachment();
		sysAttachment.setId(id);
		sysAttachment.setOriginName(originFileName);
		sysAttachment.setSize(file.getSize());
		sysAttachment.setSuffix(suffix);
		sysAttachment.setType(file.getContentType());
		if(null!=extras){
			sysAttachment.setSysId(extras.getSysId());
			sysAttachment.setTargetTable(extras.getTargetTable());
			sysAttachment.setTargetField(extras.getTargetField());
			sysAttachment.setTargetId(extras.getTargetId());
			sysAttachment.setCreater(extras.getCreater());
			sysAttachment.setCreaterId(extras.getCreaterId());
		}
		sysAttachment.setUrl("download.json?id=" + id);
		if(!savePath.endsWith(System.getProperty("file.separator"))){
			savePath = savePath + System.getProperty("file.separator");
		}
		String physicalPath = savePath + id + suffix;
		sysAttachment.setPhysicalPath(physicalPath);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(physicalPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sysAttachment;
	}
	
	/****
	 * 把文件落地到本地,且记录附件相关数据。
	 * @param multipartFiles 附件列表
	 * @param savePath 落地文件的详细路径。
	 * @return 附件列表。
	 * ***/
	public static List<SysAttachment> saveFiles(MultipartFile[] multipartFiles,Extras extras, String savePath) {
		List<SysAttachment> attachmentList=new ArrayList<SysAttachment>();
		for(MultipartFile multipartFile:multipartFiles){
			if(multipartFile.getSize()>0){
				SysAttachment attachment=saveFile(multipartFile, extras, savePath);
				attachmentList.add(attachment);
			}
		}
		return attachmentList;
	}
}
