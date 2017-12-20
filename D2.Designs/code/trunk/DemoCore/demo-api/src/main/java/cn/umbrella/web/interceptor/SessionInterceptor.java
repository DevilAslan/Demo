package cn.umbrella.web.interceptor;

/**
 * 17-1-4	jiangh	新增postHandle	设置传到页面的basePath、thirdpartyPath和titleImgGet参数
 * 
 */
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.umbrella.api.config.Constant;
import cn.umbrella.api.enums.MemrType;
import cn.umbrella.commons.util.usual.IPUtils;
import cn.umbrella.vo.CreditSessionInfo;

import com.alibaba.fastjson.JSONObject;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private SysOprationLogService oprationLogService;

	@Autowired
	private IReportStaticDataService reportStaticDataService;

	// @Autowired
	// private ISiteService siteService;

	@Value("#{properties['thirdpartyPath']}")
	private String thirdpartyPath;

	@Value("#{properties['sysId']}")
	private String sysId;

	@Value("#{properties['titleImgGet']}")
	private String titleImgGet;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String uri = request.getServletPath().substring(1);
		String referer = request.getHeader("referer");
		if (uri.endsWith(".json")&& (referer == null || !referer.contains(request.getServerName()))) {
			try {
				String ip = IPUtils.getRemoteIP(request);
				logger.info("****************  " + ip + " :referer is null");
				response.sendRedirect("http://dcs.conac.cn/image/red.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		Object obj = request.getSession().getAttribute(Constant.CREDIT_SESSION);
		if (null == obj) {
			obj = new CreditSessionInfo();
			request.getSession().setAttribute(Constant.CREDIT_SESSION, obj);
		}
//		reportStaticDataService.updatePageViewCount();// 累计访问数量+1
		CreditSessionInfo sessionInfo = (CreditSessionInfo) obj;
		addLog(sessionInfo, request.getServletPath().substring(1), request);
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String path = request.getContextPath();
		if ("/".equals(path)) {
			path = "";
		}
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		if (request.getServerPort() == 80) {
			basePath = request.getScheme() + "://" + request.getServerName()
					+ path + "/";
		}
		request.setAttribute("path", path + "/");
		request.setAttribute("basePath", basePath);
		// logger.info("******************************************************\n"
		// + "********Front Interceptor params:\n"
		// + "********basePath：" + basePath +"\n"
		// + "********path：" + path + "/\n"
		// + "******************************************************");
		request.setAttribute("thirdpartyPath", thirdpartyPath);
		request.setAttribute("sysId", sysId);
		request.setAttribute("titleImgGet", titleImgGet);
		// ReportStaticData data =
		// reportStaticDataService.queryByKey("PAGE_VIEW_COUNT");
		// int pageView = data == null ? 0 :
		// Integer.valueOf(Objects.toString(data.getReportValue(), "0"));
		// request.setAttribute("pageView", pageView);
		// Map<String, Object> map = new HashMap<>();
		// List<Site> siteList = siteService.query(map);
		// request.setAttribute("siteList", siteList);
		super.postHandle(request, response, handler, modelAndView);
	}

	private void addLog(CreditSessionInfo sessionInfo, String uri,
			HttpServletRequest request) {
		SysOprationLog log = new SysOprationLog();
		log.setIp(IPUtils.getRemoteIP(request));// IP地址
		log.setSysId(sysId);// 系统id
		log.setOprateType(0);// 操作类型
		log.setSourceType(1);
		if (request.getParameterMap() != null) {
			log.setRequData(JSONObject.toJSON(request.getParameterMap())
					.toString());
		}

		if (sessionInfo != null) {
			// 个人
			if (String.valueOf(MemrType.PERSON.getValue()).equals(sessionInfo.creditType)&& StringUtils.isNoneBlank(sessionInfo.authRange)) {
				log.setUserType(Integer.parseInt(sessionInfo.authRange));
			}
			// 法人
			if (String.valueOf(MemrType.ENTERPRISE.getValue()).equals(
					sessionInfo.creditType)
					&& StringUtils.isNoneBlank(sessionInfo.authRange)) {
				log.setUserType(5 - Integer.parseInt(sessionInfo.authRange));
			}
			if (sessionInfo.getCreditName() != null) {
				log.setCreater(sessionInfo.getCreditName());// 操作用户
			}
			if (sessionInfo.getUserId() != null) {
				try {
					log.setCreaterId(Integer.parseInt(sessionInfo.getUserId()));// 操作用户ID
				} catch (NumberFormatException e) {
					log.setCreaterId(0);// 操作用户ID userId 非int类型
				}
			}
			if (log.getCreater() == null) {
				log.setCreater(MemrType.TOURIST.getName());// 操作用户
			}
			if (log.getCreaterId() == null) {
				log.setCreaterId(MemrType.TOURIST.getValue());// 操作用户ID
			}
		} else {
			log.setCreater(MemrType.TOURIST.getName());// 操作用户
			log.setCreaterId(MemrType.TOURIST.getValue());
		}
		log.setOpration(uri);// 操作内容
		oprationLogService.addSysOprationLog(log);
		
		// reportStaticDataService.updatePageViewCount();// 累计访问数量+1
		// Map<String,Object> map = new HashMap<>();
		// map.put("timeHour", new SimpleDateFormat("yyyyMMddHH").format(new
		// Date()));
		// map.put("sourceType", log.getSourceType());
		// oprationLogService.updateHourVisist(map);//小时访问量+1
	}
}
