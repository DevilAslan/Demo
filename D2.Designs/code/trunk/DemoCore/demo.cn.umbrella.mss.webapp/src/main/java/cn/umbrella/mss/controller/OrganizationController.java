package cn.umbrella.mss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.umbrella.mss.config.Constant;

@Controller
@RequestMapping(OrganizationController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class OrganizationController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/mss/ORG";
	protected static final String PAGE_PATH = "/page/ORG/";
	
	@Value("#{properties['sysId']}")
	private String sysId;
	
}
