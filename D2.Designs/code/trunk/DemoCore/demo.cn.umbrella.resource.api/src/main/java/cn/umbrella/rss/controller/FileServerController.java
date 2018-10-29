package cn.umbrella.rss.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cn.umbrella.commons.utils.base.FileTool;
import cn.umbrella.commons.utils.base.StringUtil;
import cn.umbrella.commons.utils.base.SymbolUtil;
import cn.umbrella.rss.config.Constant;

@Controller
@RequestMapping(FileServerController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class FileServerController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/server/file";
	protected static final String PAGE_PATH = "/page/file/";
	
	@Value("#{properties['fileStore']}")
	private String fileStore;
	
	@Value("#{properties['tempFileStore']}")
	private String tempFileStore;
	
	@RequestMapping(value="singleTempFileUpload", method={RequestMethod.POST})
	@ResponseBody
	public String singleTempFileUpload(@RequestParam(value = "singleFile", required = false) MultipartFile file){
		JSONObject response = new JSONObject();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			if(!StringUtil.EMPTY.equals(fileName)){
				int k = fileName.lastIndexOf(SymbolUtil.SPOT); 
				String str = fileName.substring(k+1, fileName.length());
				if(FileTool.ImgContains(str)){
					
				}else{
					
				}
			}
			
		}
		String separator = System.getProperty("file.separator");
		//do upload file
		if(!file.isEmpty()){
			String name = file.getOriginalFilename();
			int index = name.lastIndexOf(SymbolUtil.SPOT);
			String type = name.substring(index, name.length());
			String imgName = UUID.randomUUID().toString().replace(SymbolUtil.BAR, "");
			
			File path = new File(tempFileStore);
			if (!path.exists()) {
				path.mkdirs();
			}
			
			File img = new File(tempFileStore+separator+imgName+type);
			try {
				if(img.createNewFile()){
					file.transferTo(img);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return response.toString();
	}
	
	
	@RequestMapping(value="singleFileUpload", method={RequestMethod.POST})
	@ResponseBody
	public String singleFileUpload(@RequestParam(value = "singleFile", required = false) MultipartFile file){
		JSONObject response = new JSONObject();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			if(!"".equals(fileName)){
				int k = fileName.lastIndexOf(SymbolUtil.SPOT); 
//				file.getContentType()
				String str = fileName.substring(k+1, fileName.length());
				if(FileTool.ImgContains(str)){
					
				}else{
					
				}
			}
			
		}
		String separator = System.getProperty("file.separator");
		//do upload file
		if(!file.isEmpty()){
			String name = file.getOriginalFilename();
			int index = name.lastIndexOf(SymbolUtil.SPOT);
			String type = name.substring(index, name.length());
			String imgName = UUID.randomUUID().toString().replace(SymbolUtil.BAR, "");
			
			File path = new File(fileStore);
			if (!path.exists()) {
				path.mkdirs();
			}
			
			File img = new File(fileStore+separator+imgName+type);
			try {
				if(img.createNewFile()){
					file.transferTo(img);
//					content.setTitleImg(titleImgGet+contentid+type);
//					content.setTitleImg(imgName+type);
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		return response.toString();
	}
}
