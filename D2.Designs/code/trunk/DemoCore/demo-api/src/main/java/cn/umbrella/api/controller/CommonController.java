package cn.umbrella.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.umbrella.api.config.Constant;

@Controller
@RequestMapping(CommonController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class CommonController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/common/";
	
	@Value("#{properties['sysId']}")
	private String sysId;
	@Value("#{properties['commitment_book_path']}")
	private String commitment_book_path;// 承诺书路径
	@Value("#{properties['snapshootPath']}")
	private String snapshootPath;// 快照地址
	@Value("#{properties['titleImgStore']}")
	private String titleImgStore;// 标题图地址
	@Value("#{properties['fileupload.path']}")
	private String fileuploadPath;// 附件保存路径
	
	/**
	 * 图片获取 
	 *
	 * @Title:  
	 * @param imgFile
	 * @param request
	 * @param response 
	 * @return void
	 */
	@RequestMapping(value = "{option}Fetch.json", method = {RequestMethod.GET})
	public void fetchCommitmentBook(String imgFile, @PathVariable("option") String option, HttpServletRequest request, HttpServletResponse response) {
		try {
			String filePath = "";
			if (Objects.equals("commitmentBook", option)) {
				filePath = commitment_book_path + imgFile;
			} else if (Objects.equals("snapshoot", option)) {
				filePath = snapshootPath + imgFile;
			} else if (Objects.equals("titleImage", option)) {
				filePath = titleImgStore + "\\" + imgFile;
			}
			
			File file = new File(filePath);
			
			if(!(file.exists() && file.canRead())) {
				String fileName = request.getSession().getServletContext().getRealPath("/") + "images/default.png";// 默认图片
	            file = new File(fileName);
	        }
			
			FileInputStream inputStream = new FileInputStream(file);
	        byte[] data = new byte[(int)file.length()];
	        inputStream.read(data);
	        inputStream.close();

	        response.setContentType("image/png");
			OutputStream stream = response.getOutputStream();
			stream.write(data);
	        stream.flush();
	        stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
