package com.admin.service.impl.finance;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.admin.mapper.finance.DirectPurchaseOrderMapper;
import com.admin.service.finance.DirectPurchaseOrderService;

/**
 * Created by Administrator on 2016/5/12.
 */
@Service
public class DirectPurchaseOrderServiceImpl implements
		DirectPurchaseOrderService {

	@Resource
	private DirectPurchaseOrderMapper directPurchaseOrderMapper;

	@Override
	public int updateCoachesPayStatusById(Integer id,Integer status) {
		return directPurchaseOrderMapper.updateCoachesPayStatusById(id,status);
	}
	
}
