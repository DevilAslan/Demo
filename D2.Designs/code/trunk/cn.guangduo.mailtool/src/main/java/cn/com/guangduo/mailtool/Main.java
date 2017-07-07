package cn.com.guangduo.mailtool;

import java.util.Properties;

public class Main {
	private static String CHECK_URLS_KEY="checkurls";
	private static String SYSTEM_NAME_KEY="systemname";
	
    public static void main(String[] args) throws Exception {
    	Properties prop = new Properties();
        prop.load(Main.class.getResourceAsStream("/mail.properties"));
        String sysname = new String(prop.getProperty(SYSTEM_NAME_KEY).getBytes(),"UTF-8");
    	for(String url : prop.getProperty(CHECK_URLS_KEY).split(",")){
			try {
				int i = CheckUrlTool.check(url.trim());
				if(i==1){
					MailTool.sendTextMail(prop, sysname+"可能出现问题", "链接："+url+"可能存在问题，请核实！");
				}
			} catch (Exception e) {
				e.printStackTrace();
				MailTool.sendTextMail(prop, sysname+"出现问题", "链接："+url+"可能存在问题，请核实！"+e.getMessage());
			}
		}
    	System.exit(0);
    }
}
