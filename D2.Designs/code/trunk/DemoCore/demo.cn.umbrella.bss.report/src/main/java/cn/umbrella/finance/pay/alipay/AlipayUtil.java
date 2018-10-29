/**
 * 
 */
package cn.umbrella.finance.pay.alipay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import com.alibaba.fastjson.JSONObject;

import cn.umbrella.finance.dataobject.alipay.AlipayPayTradeNo;
import cn.umbrella.finance.dataobject.alipay.AlipayRefundNo;
import cn.umbrella.finance.mapper.alipay.AlipayPayTradeNoMapper;
import cn.umbrella.finance.mapper.alipay.AlipayRefundNoMapper;
import cn.umbrella.finance.pay.alipay.AlipayUtil;
import cn.umbrella.finance.util.alipay.AlipayCore;
import cn.umbrella.finance.util.alipay.AlipayNotify;
import cn.umbrella.finance.util.alipay.AlipaySubmit;
import cn.umbrella.finance.util.alipay.MD5;
import cn.umbrella.finance.util.alipay.RSA;
import cn.umbrella.finance.util.alipay.UtilDate;


/**
 * @author Gerrard
 * @date 2016年4月6日
 */
public class AlipayUtil {

	private static Logger log = Logger.getLogger(AlipayUtil.class);
	
	private String partner;
	private String app_id;
	private String seller_id;
	private String key;
	private String private_key;
	private String alipay_public_key;
	private String pay_notify_url;
	private String refund_notify_url;
	private String input_charset;
	private String payment_type;
	private String webPayType;
	private String appPayType;
	private String tarde_time;
	private String show_url;
	private AlipayPayTradeNoMapper alipayPayTradeNoMapper;
	private AlipayRefundNoMapper alipayRefundNoMapper;
	
	/**
	 * 
	 * @param payType 支付类型 1 web 2 APP
	 * @param title 商品
	 * @param payFee 支付金额
	 * @param notifyUrl 异步通知地址
	 * @param returnUrl 同步通知地址
	 * @param idGenarater
	 * @param returnUrl 返回按钮地址
	 * @return
	 */
	public JSONObject alipay(String orderNum,String payType,String title,String payFee,String notifyUrl,String returnUrl,MySQLMaxValueIncrementer idGenarater,String showUrl){
		String ids = idGenarater.nextStringValue();
		for(int i = 0; i<10;i++){
			if(ids.length()==10){
				break;
			}
			ids = "0"+ids;
		}
		String outTardeNo = getStringTimeOfNaTime(new Date())+ids;
		JSONObject res = new JSONObject();
		if(getWebPayType().equals(payType)){
			res = directPay(orderNum,outTardeNo, title, payFee, notifyUrl, returnUrl);
		}else{
			res = wapDdirectPay(orderNum,outTardeNo, title, payFee, notifyUrl, returnUrl,showUrl);
		}
		
		return res;
	}
	
	public static String getStringTimeOfNaTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateString = sdf.format(date);
		return dateString;
	} 
	
	/**
	 * PC
	 * @param tardeNo
	 * @param title
	 * @param payFee
	 * @param notifyUrl
	 * @param returnUrl
	 * @return
	 */
	private JSONObject directPay(String orderNum,String outTardeNo,String title,String payFee,String notifyUrl,String returnUrl){
		JSONObject res = new JSONObject();
		try{
			Map<String, String> params = new HashMap<String, String>();
			params.put("service", "create_direct_pay_by_user");//即时到账接口
	        params.put("partner", getPartner());
	        params.put("seller_id", getSeller_id());
	        params.put("_input_charset", getInput_charset());
			params.put("payment_type", getPayment_type());
			params.put("notify_url", getPay_notify_url());
			params.put("return_url", returnUrl);
//			params.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
//			params.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
			params.put("out_trade_no", outTardeNo);
			params.put("subject", title);
			params.put("total_fee", payFee);
//			params.put("body", "测试调用");
			params.put("qr_pay_mode", "2");
			params.put("it_b_pay", getTarde_time());
			params.put("orderNum", orderNum);
			int id = createTradno(params,getPay_notify_url());
			params.remove("orderNum");
			log.info(">>>>支付宝支付传入的params>>>>"+params);
			String result = AlipaySubmit.buildRequest(params, "post", "确认","RSA",getPrivate_key(),getInput_charset());
			res.put("id", id);
			res.put("outTardeNo", outTardeNo);
			res.put("result", result);
			res.put("code", "0");
		}catch(Exception e){
			res.put("code", "-1");
			log.error("支付宝PC支付：", e);
		}
		return res;
	}
	/**
	 * wap
	 * @param outTardeNo
	 * @param title
	 * @param payFee
	 * @param notifyUrl
	 * @param returnUrl
	 * @return
	 */
	private JSONObject wapDdirectPay(String orderNum,String outTardeNo,String title,String payFee,String notifyUrl,String returnUrl,String showUrl){
		JSONObject res = new JSONObject();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("service", "alipay.wap.create.direct.pay.by.user");//手机网站支付接口
	        params.put("partner", getPartner());
	        params.put("seller_id", getSeller_id());
	        params.put("_input_charset", getInput_charset());
			params.put("payment_type", getPayment_type());
			params.put("notify_url", getPay_notify_url());
			params.put("return_url", returnUrl);
			params.put("out_trade_no", outTardeNo);
			params.put("subject", title);
			params.put("total_fee", payFee);
			params.put("show_url", showUrl);
			params.put("body", "ok学车");
			params.put("it_b_pay", getTarde_time());
			params.put("orderNum", orderNum);
			int id = createTradno(params,getPay_notify_url());
			params.remove("orderNum");
			log.info(">>>>支付宝支付传入的params>>>>"+params);
			String result = AlipaySubmit.buildRequest(params, "post", "确认","RSA",getPrivate_key(),getInput_charset());
			res.put("id", id);
			res.put("outTardeNo", outTardeNo);
			res.put("result", result);
			res.put("code", "0");
		} catch (Exception e) {
			res.put("code", "-1");
			log.error("支付宝wap支付", e);
		}
		return res;
	}
	
	private int createTradno(Map<String, String> params,String notifyUrl){
		AlipayPayTradeNo aptn = new AlipayPayTradeNo();
		aptn.setOutTradeNo(params.get("out_trade_no"));
		aptn.setSubject(params.get("subject"));
		aptn.setTotalFee(params.get("total_fee"));
		aptn.setTradeStatus("1");
		aptn.setCreateTime(new Date());
		aptn.setUpdateTime(aptn.getCreateTime());
		aptn.setNotifyUrl(notifyUrl);
		aptn.setOrderNum(params.get("orderNum"));
		alipayPayTradeNoMapper.insertSelective(aptn);
		return aptn.getId();
	}
	/**
	 * 支付宝单笔退款申请
	 * @param outTardeNo 支付流水号
	 * @param payFee 退款金额
	 * @param reason 退款原因
	 * @param refundUrl 退款通知地址
	 * @param idGenarater
	 * @return
	 */
	@SuppressWarnings("finally")
	public JSONObject refund(String outTardeNo,String payFee,String reason,String refundUrl,MySQLMaxValueIncrementer idGenarater){
		JSONObject res =  new JSONObject();
		String ids = idGenarater.nextStringValue();
		for(int i = 0; i<10;i++){
			if(ids.length()==10){
				break;
			}
			ids = "0"+ids;
		}
		AlipayPayTradeNo aptn = alipayPayTradeNoMapper.selectByOutTradeNo(outTardeNo);
		String batch_no = getStringTimeOfNaTime(new Date())+ids;
		String batch_num = "1";
		String detail_data = aptn.getTradeNo()+"^"+payFee+"^"+reason;
		String refund_date = UtilDate.getDateFormatter();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("service", "refund_fastpay_by_platform_pwd");
	        params.put("partner", getPartner());
	        params.put("_input_charset", getInput_charset());
			params.put("notify_url", getRefund_notify_url());
			params.put("seller_user_id", getSeller_id());
			params.put("refund_date", refund_date);
			params.put("batch_no", batch_no);
			params.put("batch_num", batch_num);
			params.put("detail_data", detail_data);
			AlipayRefundNo arn = new AlipayRefundNo();
			arn.setBatchNo(batch_no);
			arn.setBatchNum(batch_num);
			arn.setRefundDate(refund_date);
			arn.setOutTradeNo(outTardeNo);
			arn.setTradeNo(aptn.getTradeNo());
			arn.setRefundFee(payFee);
			arn.setRefundStatus("1");
			arn.setCreateTime(new Date());
			arn.setUpdateTime(arn.getCreateTime());
			alipayRefundNoMapper.insertSelective(arn);
			res.put("id", arn.getId());
			String result = AlipaySubmit.buildRequest(params, "post", "确认", "RSA", getPrivate_key(),getInput_charset());
			res.put("refundNo", batch_no);
			res.put("result", result);
			res.put("code", "0");
		} catch (Exception e) {
			res.put("code", "-1");
			log.error("支付宝即时到账有密退款：", e);
		}finally{
			return res;
		}
		
		
	}
	/**
	 * 查询订单 (支付流水号与支付交易号可值传入一个 建议使用交易号查询 流水号查询效率较低)
	 * @param queryType 查询类型 1 支付查询    2 退款查询
	 * @param outTardeNo 支付流水号 
	 * @param tardeNo 支付交易号 
	 * @return
	 */
	public JSONObject query(String queryType,String outTardeNo,String tardeNo){
		JSONObject res = new JSONObject();
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("service", "single_trade_query");
			params.put("partner", getPartner());
			params.put("_input_charset", getInput_charset());
			params.put("trade_no", tardeNo);
			params.put("out_trade_no", outTardeNo);
			String result = AlipaySubmit.buildRequest("", "", params, "RSA", getPrivate_key(), getInput_charset());
			Map<String, Object> map = XmlToMapUtils.xmlToMap(result);
			Map<String, String> sMap = new HashMap<String, String>();
			String is_success = (String) map.get("is_success");
			if("T".equals(is_success)){
				map.remove("param");
				map.remove("name");
				for (Map.Entry<String, Object> entry:map.entrySet()) {
					sMap.put(entry.getKey(), (String) entry.getValue());
				}
				sMap.remove("is_success");
				String sign = (String) map.get("sign");
				String sign_type = (String) map.get("sign_type");
				Map<String, String> sParaNew = AlipayCore.paraFilter(sMap);
		        //获取待签名字符串
		        String preSignStr = AlipayCore.createLinkString(sParaNew);
		        //获得签名验证结果
		        boolean isSign = false;
				if("RSA".equals(sign_type)){
					isSign = RSA.verify(preSignStr, sign, getAlipay_public_key(), getInput_charset());
				}else {
					isSign = MD5.verify(preSignStr, sign, getKey(), getInput_charset());
				}
				if(isSign){
					res.put("code", "0");
					if("1".equals(queryType)){
						String trade_status = sMap.get("trade_status");
						if("TRADE_SUCCESS".equals(trade_status)
								||"TRADE_FINISHED".equals(trade_status)){
							res.put("trade_status", "支付成功");
							res.put("code", "1");
						}
						
					}else if("2".equals(queryType)){
						String refund_status = sMap.get("refund_status");
						if("REFUND_SUCCESS".equals(refund_status)){
							res.put("refund_status", "退款成功");
							res.put("code", "2");
						}else if("ACTIVE_REFUND".equals(refund_status)){
							res.put("refund_status", "退款中");
							res.put("code", "3");
						}
					}
					res.put("msg", "查询订单成功");
				}else{
					res.put("code", "-2");
					res.put("msg", "查询订单异常");
				}
			}else{
				res.put("code", "-1");
				if("TRADE_NOT_EXIST".equals(map.get("error"))){
					res.put("msg", "订单交易不存在");
				}else{
					res.put("msg", "查询订单失败");
				}
				
			}
			System.out.println(map);
		} catch (Exception e) {
			res.put("code", "-1");
			res.put("msg", "查询订单失败");
			log.error("支付宝查询订单：", e);
		}
		return res;
	}
	/**
	 * 支付同步通知
	 * @param request
	 * @return
	 */
	public JSONObject checkPay(HttpServletRequest request){
		JSONObject res = new JSONObject();
		Map<String, String> params = new HashMap<String, String>();
		boolean flat = false;
		try {
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = (String) iter.next();
				if("action".equals(name)){
					continue;
				}
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			String key = "";
			if ("MD5".equals(params.get("sign_type"))) {
				key = getKey();
			} else if ("RSA".equals(params.get("sign_type"))) {
				key = getAlipay_public_key();
			}
			flat = AlipayNotify
					.verify(params, getPartner(),
							params.get("sign_type"), key,
							getInput_charset());
			if(flat){
				String trade_status = params.get("trade_status");
			    if("TRADE_SUCCESS".equals(trade_status)){//支付成功
					AlipayPayTradeNo aptn = alipayPayTradeNoMapper.selectByOutTradeNo(params.get("out_trade_no"));
					if("1".equals(aptn.getTradeStatus())){
						aptn.setOutTradeNo(params.get("out_trade_no"));
						aptn.setTradeNo(params.get("trade_no"));
						aptn.setTradeStatus("2");
						aptn.setSellerEmail(params.get("seller_email"));
						aptn.setBuyerEmail(params.get("buyer_email"));
						aptn.setUpdateTime(new Date());
						alipayPayTradeNoMapper.updateByPrimaryKeySelective(aptn);
					}
				}
			    res.put("code", "0");
			    res.put("out_trade_no", params.get("out_trade_no"));
			}else{
				res.put("code", "-1");
			}
		} catch (Exception e) {
			res.put("code", "-1");
			log.error("支付宝支付通知", e);
		}
		return res;
	}
	
	protected AlipayUtil() {
		
	}

	/**
	 * @param partner
	 * @param app_id
	 * @param seller_id
	 * @param key
	 * @param private_key
	 * @param alipay_public_key
	 * @param pay_notify_url
	 * @param refund_notify_url
	 * @param input_charset
	 * @param payment_type
	 * @param webPayType
	 * @param appPayType
	 * @param tarde_time
	 * @param show_url
	 */
	protected AlipayUtil(String partner, String app_id, String seller_id,
			String key, String private_key, String alipay_public_key,
			String pay_notify_url, String refund_notify_url,
			String input_charset, String payment_type, String webPayType,
			String appPayType,String tarde_time,String show_url,
			AlipayPayTradeNoMapper alipayPayTradeNoMapper,
			AlipayRefundNoMapper alipayRefundNoMapper) {
		this.partner = partner;
		this.app_id = app_id;
		this.seller_id = seller_id;
		this.key = key;
		this.private_key = private_key;
		this.alipay_public_key = alipay_public_key;
		this.pay_notify_url = pay_notify_url;
		this.refund_notify_url = refund_notify_url;
		this.input_charset = input_charset;
		this.payment_type = payment_type;
		this.webPayType = webPayType;
		this.appPayType = appPayType;
		this.tarde_time = tarde_time;
		this.show_url = show_url;
		this.alipayPayTradeNoMapper = alipayPayTradeNoMapper;
		this.alipayRefundNoMapper = alipayRefundNoMapper;
	}
	/**
	 * @return the partner
	 */
	public String getPartner() {
		return partner;
	}

	/**
	 * @return the app_id
	 */
	public String getApp_id() {
		return app_id;
	}

	/**
	 * @return the seller_id
	 */
	public String getSeller_id() {
		return seller_id;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the private_key
	 */
	public String getPrivate_key() {
		return private_key;
	}

	/**
	 * @return the alipay_public_key
	 */
	public String getAlipay_public_key() {
		return alipay_public_key;
	}

	/**
	 * @return the notify_url
	 */
	public String getPay_notify_url() {
		return pay_notify_url;
	}

	/**
	 * @return the refund_notify_url
	 */
	public String getRefund_notify_url() {
		return refund_notify_url;
	}

	/**
	 * @return the input_charset
	 */
	public String getInput_charset() {
		return input_charset;
	}

	/**
	 * @return the payment_type
	 */
	public String getPayment_type() {
		return payment_type;
	}
	/**
	 * @return the webPayType
	 */
	public String getWebPayType() {
		return webPayType;
	}
	/**
	 * @return the appPayType
	 */
	public String getAppPayType() {
		return appPayType;
	}
	/**
	 * @return the tarde_time
	 */
	public String getTarde_time() {
		return tarde_time;
	}
	
	/**
	 * @return the show_url
	 */
	public String getShow_url() {
		return show_url;
	}
	

}
