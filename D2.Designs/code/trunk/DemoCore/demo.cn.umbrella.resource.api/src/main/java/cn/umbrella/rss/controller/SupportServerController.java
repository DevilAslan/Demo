package cn.umbrella.rss.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.umbrella.rss.config.Constant;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
@RequestMapping(SupportServerController.ACTION_PATH)
@SessionAttributes(Constant.CREDIT_SESSION)
public class SupportServerController {
	protected Logger logger = Logger.getLogger(this.getClass());
	protected static final String ACTION_PATH = "/server/file";
	protected static final String PAGE_PATH = "/page/file/";
	
	@Autowired
    DefaultKaptcha defaultKaptcha;
	
	/**
     * 图形验证码
     * @param sessionInfo
     * @param response
     * @throws Exception
     */
    @RequestMapping("imagecode")
    public void imagecode(
//    		@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
    		HttpServletResponse response) throws Exception {//HttpSession session
        ByteArrayOutputStream pngOPS = new ByteArrayOutputStream();
        try {
            //生产验证码字符串
            String vrifyCode = defaultKaptcha.createText();
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage vrifyCodeImg = defaultKaptcha.createImage(vrifyCode);
            ImageIO.write(vrifyCodeImg, "png", pngOPS);
            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            byte[] captchaChallengeAsPng = pngOPS.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/png");
            ServletOutputStream responseOutputStream = response.getOutputStream();
            responseOutputStream.write(captchaChallengeAsPng);
            responseOutputStream.flush();
            responseOutputStream.close();
            //保存到session中
//            sessionInfo.setVrifyCode(vrifyCode.toLowerCase());
//            sessionInfo.setVerifyCodeTimeout(Calendar.getInstance().getTime().getTime() + VERIFYCODE_TIMEOUT * 1000);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            logger.error("验证码生成失败");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }
}
