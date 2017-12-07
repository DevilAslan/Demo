package cn.umbrella.oss.controller.wechat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cn.umbrella.mboss.po.SysUser;
import cn.umbrella.oss.config.Constant;
import cn.umbrella.oss.vo.MySessionInfo;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Base64Uploader;

@Controller
@RequestMapping("/wxFileUpload/")
@SessionAttributes({Constant.MY_SESSION_INFO})
public class WXFileController {
	@Autowired
	private SysAttachmentService sysAttachmentService;
	
	@Value("#{properties['fileupload.path']}")
	private String rootPath;
	
	@Value("#{properties['fileupload.configPath']}")
	private String configPath;
	
	private String actionType = null;
	private ConfigManager configManager = null;
	
	@Value("#{properties['sysId']}")
	private String sysId;
	
	/**
	 * 文件上传
	 * @return
	 */
	@RequestMapping(value="upload.json", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody String upload(HttpServletRequest request, FileQuery fileQuery){
		this.actionType = request.getParameter( "action" );
		this.configManager = ConfigManager.getInstance(this.rootPath, request.getSession().getServletContext().getRealPath("/")+configPath);
		String start_ = request.getParameter( "start" );
		Integer start = 0; 
		try {
			if(start_ != null){
				start = Integer.parseInt(start_);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		
		String callbackName = request.getParameter("callback");
		if ( callbackName != null ) {
			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJSONString();
			}
			return callbackName+"("+this.invoke(actionType, configManager, request, start, fileQuery)+");";
		} else {
			return this.invoke(actionType, configManager, request, start, fileQuery);
		}
	}
	
	public String invoke(String actionType, ConfigManager configManager, HttpServletRequest request, Integer start, FileQuery fileQuery) {
		if (actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
		
		if (this.configManager == null || !this.configManager.valid() ) {
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
		}
		
		State state = null;
		int actionCode = ActionMap.getType( this.actionType );
		Map<String, Object> conf = null;
		switch ( actionCode ) {
			case ActionMap.CONFIG:
				return this.configManager.getAllConfig().toString();
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = this.configManager.getConfig( actionCode );
				String filedName = (String) conf.get("fieldName");
				String savePath = (String) conf.get("savePath");
				if(fileQuery.getUpfile() == null){
					return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
				} else {
					if ("true".equals(conf.get("isBase64"))) {
						state = Base64Uploader.save(request.getParameter(filedName), conf);
					} else {
						for(MultipartFile file : fileQuery.getUpfile()){
				            if(file.isEmpty()){
				            	return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA).toJSONString();
				            }else{
				                String originFileName = file.getOriginalFilename();
								String suffix = FileType.getSuffixByFilename(originFileName);
								
								if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
									return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE).toJSONString();
								}
								
								long maxSize = ((Long) conf.get("maxSize")).longValue();
								if (file.getSize() > maxSize) {
									return new BaseState(false, AppInfo.MAX_SIZE).toJSONString();
								}
								String id = UUID.randomUUID().toString();
								savePath = savePath + id + suffix;
								savePath = PathFormat.parse(savePath, originFileName);
								String physicalPath = (String) conf.get("rootPath") + savePath;
								SysAttachment sysAttachment = new SysAttachment();
			                	sysAttachment.setId(id);
			                	sysAttachment.setSysId(sysId);
								sysAttachment.setCreater("admin");
								sysAttachment.setCreaterId(1);
								sysAttachment.setOriginName(originFileName);
								sysAttachment.setPhysicalPath(physicalPath);
								sysAttachment.setSize(file.getSize());
								sysAttachment.setSuffix(suffix);
								sysAttachment.setType(file.getContentType());
								sysAttachment.setUrl("download.json?id="+id);
								
				                try {
									FileUtils.copyInputStreamToFile(file.getInputStream(), new File(physicalPath));
									sysAttachmentService.addSysAttachment(sysAttachment);
									
									state = new BaseState(true);
									state.putInfo("url", request.getScheme()+"://"+request.getServerName() +":"+ request.getServerPort() + request.getContextPath()+"/" + sysAttachment.getUrl());
									state.putInfo("type", suffix);
									state.putInfo("original", originFileName);
								} catch (IOException e) {
									e.printStackTrace();
								}
				            }
				        }
					}
				}
				break;
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				String[] list = request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( conf ).capture( list );
				break;
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig(actionCode);
				state = new FileManager(conf).listFile(start);
				break;
		}
		return state.toJSONString();
	}

	/**
	 * 文件下载
	 * @return
	 */
	@RequestMapping(value="download.json", method = {RequestMethod.GET, RequestMethod.POST})
	public void download(String id, HttpServletRequest request, HttpServletResponse response){
		SysAttachment sysAttachment = sysAttachmentService.getSysAttachmentById(id);
		if(sysAttachment == null){
			return;
		}
		FileInputStream fis = null;
		try {
			response.setContentType(sysAttachment.getType());
			response.setContentLength(sysAttachment.getSize().intValue());
			response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(sysAttachment.getOriginName(),"UTF-8") + "\"");
			OutputStream out = response.getOutputStream();
			File file = new File(sysAttachment.getPhysicalPath());
			fis = new FileInputStream(file);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		if ( name.matches( "^[a-zA-Z_]+[\\w0-9_]*$" ) ) {
			return true;
		}
		return false;
	}
	
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
	
	/**
	 * 附件上传 
	 *
	 * @Title: uploadAttachment 
	 * @param Filedata
	 * @param extras
	 * @param sessionInfo
	 * @throws Exception 
	 * @return ResponseData
	 */
	@RequestMapping(value = "uploadAttachment.json")
	@ResponseBody
	public ResponseData uploadAttachment(MultipartFile Filedata, Extras extras, @ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo) throws Exception {
		extras.setSysId(sysId);
		SysUser user = sessionInfo.myUser;//SessionUtil.getSysUser();
		extras.setCreater(user.getName());
		extras.setCreaterId(user.getUserId());
		SysAttachment attachments = FileTool.saveFile(Filedata, extras, rootPath);
		int addNum = sysAttachmentService.addSysAttachment(attachments);
		ResponseData rd = new ResponseData();
		if (addNum > 0) {
			rd.addData("attachmentId", attachments.getId());
			rd.addData("flag", "成功");
		}
		return rd;
	}

	@RequestMapping(value = "downloadAttachment.json",params={"id"},  method = { RequestMethod.GET, RequestMethod.POST })
	public void downloadAttachment(String id, HttpServletRequest request, HttpServletResponse response) {
		SysAttachment sysAttachment = null;
		if (id != null) {
			sysAttachment = sysAttachmentService.getSysAttachmentById(id);
		}
		if (sysAttachment == null) {
			return;
		}
		downloadFileOperation(request, response, sysAttachment);
	}
	
	@RequestMapping(value = "downloadAttachment.json", params={"targetId"}, method = { RequestMethod.GET, RequestMethod.POST })
	public void downloadAttachment(Extras extras, HttpServletRequest request, HttpServletResponse response) {
		SysAttachment sysAttachment = null;
		List<SysAttachment> sysAttachments = sysAttachmentService.getAttachmentByExtras(extras);
		if (sysAttachments != null && sysAttachments.size() > 0) {
			sysAttachment = sysAttachments.get(0);
		}
		if (sysAttachment == null) {
			return;
		}
		downloadFileOperation(request, response, sysAttachment);
	}
	
	/**
	 * 
	 * @Description: 根据目标表、目标字段、目标记录ID获取附件信息
	 * @Title: getAttachements
	 * @param extras
	 * @return List<SysAttachment>
	 * @throws
	 */
	@RequestMapping(value = "getAttachements.json", method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseData getAttachements(Extras extras) {
		List<SysAttachment> sysAttachments = sysAttachmentService.getAttachmentByExtras(extras);
		ResponseData ard = new ResponseData();
		ard.put("sysAttachments", sysAttachments);
		return ard;
	}
	
	private void downloadFileOperation(HttpServletRequest request, HttpServletResponse response, SysAttachment sysAttachment) {
		FileInputStream fis = null;
		try {
			OutputStream out = response.getOutputStream();
			File file = new File(sysAttachment.getPhysicalPath());
			if (file.exists()) {
				response.setContentType(sysAttachment.getType());
				response.setContentLength(sysAttachment.getSize().intValue());
				String originName = sysAttachment.getOriginName();
				String agent = request.getHeader("user-agent");
				// 处理浏览器
				if (agent.contains("Firefox")) {
					originName = "=?UTF-8?B?"
							+ new String(new Base64().encode(originName
									.getBytes("UTF-8"))) + "?=";
				} else {
					// 其它浏览器 IE 、google 采用URL编码
					originName = URLEncoder.encode(originName, "utf-8");
					originName = originName.replace("+", " ");
				}
				response.setHeader("Content-Disposition", "inline; filename="
						+ originName);
				fis = new FileInputStream(file);
				byte[] b = new byte[fis.available()];
				fis.read(b);
				out.write(b);
				out.flush();
			} else {
				response.sendRedirect("images/notexist.png");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 删除附件
	 *
	 * @Title: deleteAttachment 
	 * @param id
	 * @return ResponseData
	 */
	@RequestMapping(value = "deleteAttachment.json", method = {RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseData deleteAttachment(String id) {
		ResponseData ard = new ResponseData();
		sysAttachmentService.delSysAttachment(id);
		return ard;
	}
}
