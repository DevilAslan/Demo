package cn.umbrella.commons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller
 * @author Aslan
 * @version 2017-06-05
 */
@Controller
@RequestMapping(IBaseController.ACTION_PATH)
public class IBaseController {
	protected static final String ACTION_PATH = "/base";
}