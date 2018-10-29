package com.admin.mapper.finance;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/5/12.
 */
public interface DirectPurchaseOrderMapper {

	 //财务系统点击支付给教练打款完成后修改订单中的coachesPayStatus为2已打款,点击申请打款后coachesPayStatus修改为1申请打款
    int updateCoachesPayStatusById(@Param("id") Integer id,@Param("status") Integer status);

}
