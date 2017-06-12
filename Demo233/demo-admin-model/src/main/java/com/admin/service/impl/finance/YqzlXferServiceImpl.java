package com.admin.service.impl.finance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.admin.mapper.finance.YqzlXferMapper;
import com.admin.model.finance.YqzlXfer;
import com.admin.service.finance.YqzlXferService;

@Service
public class YqzlXferServiceImpl implements YqzlXferService{
	
	@Resource
	private YqzlXferMapper yqzlXferMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return yqzlXferMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(YqzlXfer record) {
		return yqzlXferMapper.insert(record);
	}

	@Override
	public int insertSelective(YqzlXfer record) {
		return yqzlXferMapper.insertSelective(record);
	}

	@Override
	public YqzlXfer selectByPrimaryKey(Integer id) {
		return yqzlXferMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(YqzlXfer record) {
		return yqzlXferMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(YqzlXfer record) {
		return yqzlXferMapper.updateByPrimaryKey(record);
	}

	@Override
	@Transactional
	public int insertBatchXferSelective(List<YqzlXfer> list) {
		for (YqzlXfer yqzlXfer : list) {
			yqzlXferMapper.insertSelective(yqzlXfer);
		}
		return 0;
	}

	@Override
	public int selectCountByOrderIdAndState(Integer orderId, Integer state) {
		return yqzlXferMapper.selectCountByOrderIdAndState(orderId,state);
	}

	@Override
	public int selectCountByPaymentIdAndState(Integer paymentId, Integer state) {
		return yqzlXferMapper.selectCountByPaymentIdAndState(paymentId,state);
	}

}
