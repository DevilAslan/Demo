package com.admin.service.finance;

import java.util.List;

import com.admin.model.finance.YqzlXfer;

public interface YqzlXferService {
	
    int deleteByPrimaryKey(Integer id);

    int insert(YqzlXfer record);

    int insertSelective(YqzlXfer record);

    YqzlXfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YqzlXfer record);

    int updateByPrimaryKey(YqzlXfer record);
    
    int insertBatchXferSelective(List<YqzlXfer> list);
    
    int selectCountByOrderIdAndState(Integer orderId,Integer state);
    
    int selectCountByPaymentIdAndState(Integer paymentId,Integer state);
}
