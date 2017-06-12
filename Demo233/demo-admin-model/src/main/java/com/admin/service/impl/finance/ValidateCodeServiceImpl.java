package com.admin.service.impl.finance;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.admin.mapper.finance.ValidateCodeMapper;
import com.admin.model.finance.ValidateCode;
import com.admin.service.finance.ValidateCodeService;
import com.commons.util.DateUtil;
import com.commons.util.SmsUtil;

@Service
public class ValidateCodeServiceImpl implements ValidateCodeService{
	
	private static final Logger logger = Logger.getLogger(ValidateCodeServiceImpl.class);
	@Resource
	private ValidateCodeMapper validateCodeMapper;
	

	@Override
	public boolean sendNewAppPhoneCode(String phone,Integer type) {
		try {
			/*ValidateCode validateCode = validateCodeMapper.selectByPhone(phone);
			if(null != validateCode){
				int created = validateCode.getCreated();
				int nowSecond = DateUtil.getNowSecond();
				if (nowSecond - created <= (10 * 60)) {
					
				}
			}else{
				
			}*/

			
			//phoneCodeDao.insert(phoneCode);

			if (type == 0) {
				//SmsUtil.jxValidCodeSend(phone, phoneCode.getCode());
			} else {
				String code = genRandomCode();
				SmsUtil.studentValidCodeSend(phone, code);
				ValidateCode validateCode = new ValidateCode();
				validateCode.setPhone(phone);
				validateCode.setCode(code);
				validateCode.setCreated(DateUtil.getNowSecond());
				validateCodeMapper.insertValidateCode(validateCode);
			}

			return true;
			
		}catch(Exception e){
			logger.error("Fail to send Phone Code to : " + phone,
					e);
			return false;
		}
		
	}
	
	private String genRandomCode() {
		double rvalue = Math.random();
		if (rvalue == 0)
			rvalue = Math.random();
		long value = (long) (rvalue * 1329000111) + 724355
				+ (long) (Math.random() * 23444);
		String code = String.valueOf(value);
		if (code.length() > 6) {
			return code.substring(code.length() - 6);
		}
		return code;
	}

	@Override
	public ValidateCode selectValidateCodeByPhone(String phone) {
		return validateCodeMapper.selectByPhone(phone);
	}

	@Override
	public boolean sendYqzlXferPhoneCode(String phone) {
		try{
			String code = genRandomCode();
			SmsUtil.financeValidCodeSend(phone, code);
			ValidateCode validateCode = new ValidateCode();
			validateCode.setPhone(phone);
			validateCode.setCode(code);
			validateCode.setCreated(DateUtil.getNowSecond());
			validateCodeMapper.insertValidateCode(validateCode);
		}catch(Exception e){
			logger.error("Fail to send Phone Code to : " + phone,
					e);
			return false;
		}
		return true;
	}
	
	
	

}
