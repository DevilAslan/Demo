package cn.umbrella.oss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.umbrella.oss.config.Constant;

@Controller
@RequestMapping(ReportController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class ReportController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/mss/report";
	protected static final String PAGE_PATH = "/page/report/";
	
	@Value("#{properties['sysId']}")
	private String sysId;
	
}
