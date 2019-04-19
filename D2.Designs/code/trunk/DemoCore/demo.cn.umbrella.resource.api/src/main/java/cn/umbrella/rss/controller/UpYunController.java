package cn.umbrella.rss.controller;

import cn.umbrella.rss.config.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(UpYunController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class UpYunController {
	protected static final String ACTION_PATH = "/res/upyun";
	protected static final String PAGE_PATH = "/page/upyun/";
	
	@Value("#{properties['sysId']}")
	private String sysId;
	
}
