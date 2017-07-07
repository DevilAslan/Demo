package com.admin.service.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.admin.model.finance.DirectPurchaseCoachesPayment;
import com.commons.model.Pagination;


public interface DirectPurchaseCoachesPaymentService {
	
	//插入教练提现流水
	int insertDirectPurchaseCoachesPayment(DirectPurchaseCoachesPayment directPurchaseCoachesPayment);
	
	//查询教练所有提取记录列表
	List<Map<String,Object>> selectListByCoachesId(Integer coachesId);
	
	//查询教练总提取金额
	int selectCountByCoachesId(Integer coachesId);
	
	//根据类型分页查询提现的记录
	Pagination<Map<String,Object>> selectPageListByStatusTypeAndName(Integer status,String coachesName,Integer type, int curPage,
			int pageSize);
	
	
	//根据类型,区域，教练姓名分页查询提现的记录
	Pagination<Map<String,Object>> selectPageListByStatusTypeRegionNameAndName(Integer status,String coachesName,String regionName,
			Integer type, int curPage,int pageSize);
	
	int updateCoachesPayStatusById(Integer id,Integer status);
	
	int updateCoachesPayStatusAndTimeById(Integer id,Integer status,Date payTime);
	
	//根据用户id查询用户信息
	Map<String, Object> findUserInfo(Integer coachesId);

}
