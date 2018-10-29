package com.admin.dataobject.finance;


import com.admin.model.finance.DirectPurchaseCoachesPayment;

public class DirectPurchaseCoachesPaymentDataObject  extends DirectPurchaseCoachesPayment{
	
	private String coachesName;

	public String getCoachesName() {
		return coachesName;
	}

	public void setCoachesName(String coachesName) {
		this.coachesName = coachesName;
	}
	
	private String regionName;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	
}