package com.admin.mapper.finance;

import org.apache.ibatis.annotations.Param;

import com.admin.model.finance.ValidateCode;

public interface ValidateCodeMapper {
	
	//插入手机验证码
	void insertValidateCode(ValidateCode validateCode);
	
	ValidateCode selectByPhone(@Param("phone") String phone);
}
