package com.admin.service.impl.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.admin.mapper.finance.DirectPurchaseCoachesPaymentMapper;
import com.admin.model.finance.DirectPurchaseCoachesPayment;
import com.admin.service.finance.DirectPurchaseCoachesPaymentService;
import com.commons.model.Pagination;



@Service
public class DirectPurchaseCoachesPaymentServiceImpl implements DirectPurchaseCoachesPaymentService{
	
	@Resource
	private DirectPurchaseCoachesPaymentMapper directPurchaseCoachesPaymentMapper;
	
	@Override
	public int insertDirectPurchaseCoachesPayment(
			DirectPurchaseCoachesPayment directPurchaseCoachesPayment) {
		return directPurchaseCoachesPaymentMapper.insertSelective(directPurchaseCoachesPayment);
	}

	@Override
	public List<Map<String, Object>> selectListByCoachesId(Integer coachesId) {
		return directPurchaseCoachesPaymentMapper.selectListByCoachesId(coachesId);
	}

	@Override
	public int selectCountByCoachesId(Integer coachesId) {
		return directPurchaseCoachesPaymentMapper.selectCountByCoachesId(coachesId);
	}

	@Override
	public Pagination<Map<String,Object>> selectPageListByStatusTypeAndName(Integer status,String coachesName,
			Integer type, int curPage, int pageSize) {
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> crcset = new HashSet<Integer>();
		HashMap<String, Object> tuitionMap = new HashMap<String, Object>();
		HashMap<String, Object> crcMap = new HashMap<String, Object>();
		Pagination<Map<String,Object>> result = new Pagination<Map<String,Object>>();
		result.setCurPage(curPage);
		result.setPageSize(pageSize);
		if (result.isAutoRows())
	    {
			int rowCount = directPurchaseCoachesPaymentMapper.countAllByStatusCoachesNameAndType(status,coachesName,type);
			result.setRowCount(rowCount);
	    }
		Long pageStart = (curPage - 1L) * pageSize;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = directPurchaseCoachesPaymentMapper.selectPaginationListByStatusCoachesNameAndType(status,coachesName,type,pageStart,pageSize);
		for (Map<String, Object> map : list) {
			if((Integer)map.get("type") == 0){//学费
				set.add((Integer)map.get("coachesId"));
			}else if((Integer)map.get("type") == 2){//教推教
				crcset.add((Integer)map.get("coachesId"));
			}
		}
		if(set.size()>0){
			for (Integer coachesId : set) {
				 //查询教练学费总收入
	            int  tuitionTotalIncome= directPurchaseCoachesPaymentMapper.selectCoachesTuitionTotalIncome(coachesId);
	            //查询财务已支付教练学费
	            int financePayTuition = directPurchaseCoachesPaymentMapper.selectFinancePayTuition(coachesId);
	            int financePayTuitionExtract = 0;
	            
	            if(status==0){
	            	//查询教练学费提现已支付或审核通过的总金额
		            financePayTuitionExtract = directPurchaseCoachesPaymentMapper.countExtractByCoachesIdAndType(coachesId,0);
	            }else if(status==1){
	            	//查询教练学费提现已支付的总金额
	            	financePayTuitionExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(coachesId,0);
	            }
	            
	            
	            int directPurchaseDeductMoney = 0;
	            
	            //查询该教练是否存在科目一大于等于2个否的评价记录
	            int gradeCount = directPurchaseCoachesPaymentMapper.selectGradeCountByCoachesId(coachesId);
	            
	            if(gradeCount>0){
	            	//查询教练财务未支付且科目一评价为2个否及以上的专教订单的需扣除金额
		            directPurchaseDeductMoney = directPurchaseCoachesPaymentMapper.selectDirectPurchaseDeductMoneyByCoachesId(coachesId);
	            }
	          
	            //实际教练可提学费收入 = 教练学费总收入 - 财务已支付教练学费 - 教练学费提现已支付或审核通过的总金额
	            int actualCanExtractTuitionIncome = tuitionTotalIncome - financePayTuition - financePayTuitionExtract-directPurchaseDeductMoney;
	            if(actualCanExtractTuitionIncome<0){
	            	actualCanExtractTuitionIncome = 0;
	            }
	            
	            tuitionMap.put(coachesId+"", actualCanExtractTuitionIncome);
			}
			
		}
		
		if(crcset.size()>0){
			for (Integer crcCoachesId : crcset) {
				 //查询教推教总收入
	            int  crcTotalIncome= directPurchaseCoachesPaymentMapper.selectCrcTotalIncome(crcCoachesId);
	            //查询财务已支付教推教
	            int financePayCrc = directPurchaseCoachesPaymentMapper.selectFinancePayCrc(crcCoachesId);
	            int financePayCrcExtract = 0;
	            if(status==0){
	            	//查询教练教推教提现已支付或审核通过的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractByCoachesIdAndType(crcCoachesId,2);
	            }else if(status==1){
	            	//查询教练教推教提现已支付的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(crcCoachesId,2);
	            }else if(status==3){
	            	//查询教练教推教提现已支付的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(crcCoachesId,2);
	            }
	            
	            //实际教练可提教推教收入 = 教推教总收入 - 财务已支付教推教金额 - 教练教推教提现已支付或审核通过的总金额
	            int actualCanExtractCrcIncome = crcTotalIncome - financePayCrc - financePayCrcExtract;
	            if(actualCanExtractCrcIncome<0){
	            	actualCanExtractCrcIncome = 0;
	            }
	            
	            crcMap.put(crcCoachesId+"", actualCanExtractCrcIncome);
				
			}
			
		}
		
		
		
		for (Map<String, Object> paymentMap : list) {
			if((Integer)paymentMap.get("type") == 0){
				int actualCanExtractTuitionIncome = (int) tuitionMap.get(paymentMap.get("coachesId")+"");
				paymentMap.put("actualCanExtractTuitionIncome", actualCanExtractTuitionIncome);
				
				int price = (int)paymentMap.get("price");
				if(price>actualCanExtractTuitionIncome){
					paymentMap.put("isSpecial", 1);
				}else{
					paymentMap.put("isSpecial", 0);
				}
				
			}else if((Integer)paymentMap.get("type") == 2){
				int actualCanExtractCrcIncome = (int) crcMap.get(paymentMap.get("coachesId")+"");
				paymentMap.put("actualCanExtractCrcIncome", actualCanExtractCrcIncome);
				
				int price = (int)paymentMap.get("price");
				if(price>actualCanExtractCrcIncome){
					paymentMap.put("isSpecial", 1);
				}else{
					paymentMap.put("isSpecial", 0);
				}
			}
		}
		
		result.setResults(list);
		return result;
	}

	@Override
	public int updateCoachesPayStatusById(Integer id,Integer status) {
		return directPurchaseCoachesPaymentMapper.updateCoachesPayStatusById(id,status);
	}

	@Override
	public Map<String, Object> findUserInfo(Integer coachesId) {
		return directPurchaseCoachesPaymentMapper.findUserInfo(coachesId);
	}

	@Override
	public int updateCoachesPayStatusAndTimeById(Integer id, Integer status,
			Date payTime) {
		return directPurchaseCoachesPaymentMapper.updateCoachesPayStatusAndTimeById(id,status,payTime);
	}

	@Override
	public Pagination<Map<String, Object>> selectPageListByStatusTypeRegionNameAndName(
			Integer status, String coachesName, String regionName,
			Integer type, int curPage, int pageSize) {
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> crcset = new HashSet<Integer>();
		HashMap<String, Object> tuitionMap = new HashMap<String, Object>();
		HashMap<String, Object> crcMap = new HashMap<String, Object>();
		Pagination<Map<String,Object>> result = new Pagination<Map<String,Object>>();
		result.setCurPage(curPage);
		result.setPageSize(pageSize);
		if (result.isAutoRows())
	    {
			int rowCount = directPurchaseCoachesPaymentMapper.countAllByStatusCoachesNameRegionNameAndType(status,coachesName,regionName,type);
			result.setRowCount(rowCount);
	    }
		Long pageStart = (curPage - 1L) * pageSize;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = directPurchaseCoachesPaymentMapper.selectPaginationListByStatusCoachesNameRegionNameAndType(status,coachesName,regionName,type,pageStart,pageSize);
		for (Map<String, Object> map : list) {
			if((Integer)map.get("type") == 0){//学费
				set.add((Integer)map.get("coachesId"));
			}else if((Integer)map.get("type") == 2){//教推教
				crcset.add((Integer)map.get("coachesId"));
			}
		}
		if(set.size()>0){
			for (Integer coachesId : set) {
				 //查询教练学费总收入
	            int  tuitionTotalIncome= directPurchaseCoachesPaymentMapper.selectCoachesTuitionTotalIncome(coachesId);
	            //查询财务已支付教练学费
	            int financePayTuition = directPurchaseCoachesPaymentMapper.selectFinancePayTuition(coachesId);
	            int financePayTuitionExtract = 0;
	            
	            if(status==0){
	            	//查询教练学费提现已支付或审核通过的总金额
		            financePayTuitionExtract = directPurchaseCoachesPaymentMapper.countExtractByCoachesIdAndType(coachesId,0);
	            }else if(status==1){
	            	//查询教练学费提现已支付的总金额
	            	financePayTuitionExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(coachesId,0);
	            }
	            
	            
	            int directPurchaseDeductMoney = 0;
	            
	            //查询该教练是否存在科目一大于等于2个否的评价记录
	            int gradeCount = directPurchaseCoachesPaymentMapper.selectGradeCountByCoachesId(coachesId);
	            
	            if(gradeCount>0){
	            	//查询教练财务未支付且科目一评价为2个否及以上的专教订单的需扣除金额
		            directPurchaseDeductMoney = directPurchaseCoachesPaymentMapper.selectDirectPurchaseDeductMoneyByCoachesId(coachesId);
	            }
	          
	            //实际教练可提学费收入 = 教练学费总收入 - 财务已支付教练学费 - 教练学费提现已支付或审核通过的总金额
	            int actualCanExtractTuitionIncome = tuitionTotalIncome - financePayTuition - financePayTuitionExtract-directPurchaseDeductMoney;
	            if(actualCanExtractTuitionIncome<0){
	            	actualCanExtractTuitionIncome = 0;
	            }
	            
	            tuitionMap.put(coachesId+"", actualCanExtractTuitionIncome);
			}
			
		}
		
		if(crcset.size()>0){
			for (Integer crcCoachesId : crcset) {
				 //查询教推教总收入
	            int  crcTotalIncome= directPurchaseCoachesPaymentMapper.selectCrcTotalIncome(crcCoachesId);
	            //查询财务已支付教推教
	            int financePayCrc = directPurchaseCoachesPaymentMapper.selectFinancePayCrc(crcCoachesId);
	            int financePayCrcExtract = 0;
	            if(status==0){
	            	//查询教练教推教提现已支付或审核通过的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractByCoachesIdAndType(crcCoachesId,2);
	            }else if(status==1){
	            	//查询教练教推教提现已支付的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(crcCoachesId,2);
	            }else if(status==3){
	            	//查询教练教推教提现已支付的总金额
	            	financePayCrcExtract = directPurchaseCoachesPaymentMapper.countExtractPayByCoachesIdAndType(crcCoachesId,2);
	            }
	            
	            //实际教练可提教推教收入 = 教推教总收入 - 财务已支付教推教金额 - 教练教推教提现已支付或审核通过的总金额
	            int actualCanExtractCrcIncome = crcTotalIncome - financePayCrc - financePayCrcExtract;
	            if(actualCanExtractCrcIncome<0){
	            	actualCanExtractCrcIncome = 0;
	            }
	            
	            crcMap.put(crcCoachesId+"", actualCanExtractCrcIncome);
				
			}
			
		}
		
		
		
		for (Map<String, Object> paymentMap : list) {
			if((Integer)paymentMap.get("type") == 0){
				int actualCanExtractTuitionIncome = (int) tuitionMap.get(paymentMap.get("coachesId")+"");
				paymentMap.put("actualCanExtractTuitionIncome", actualCanExtractTuitionIncome);
				
				int price = (int)paymentMap.get("price");
				if(price>actualCanExtractTuitionIncome){
					paymentMap.put("isSpecial", 1);
				}else{
					paymentMap.put("isSpecial", 0);
				}
			}else if((Integer)paymentMap.get("type") == 2){
				int actualCanExtractCrcIncome = (int) crcMap.get(paymentMap.get("coachesId")+"");
				paymentMap.put("actualCanExtractCrcIncome", actualCanExtractCrcIncome);
				
				int price = (int)paymentMap.get("price");
				if(price>actualCanExtractCrcIncome){
					paymentMap.put("isSpecial", 1);
				}else{
					paymentMap.put("isSpecial", 0);
				}
			}
		}
		
		result.setResults(list);
		return result;
	}

}
