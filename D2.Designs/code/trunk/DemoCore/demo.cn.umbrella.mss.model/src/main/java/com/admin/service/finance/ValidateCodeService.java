package com.admin.service.finance;

import com.admin.model.finance.ValidateCode;


public interface ValidateCodeService {
	
	//发送验证码并插入数据库
	boolean sendNewAppPhoneCode(String phone,Integer type);
	
	//根据手机号查询最新的一条验证码记录
	ValidateCode selectValidateCodeByPhone(String phone);
	
	//银企直联转账发送验证码并插入数据库
	boolean sendYqzlXferPhoneCode(String phone);
}
