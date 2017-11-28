package com.admin.model.finance;

import lombok.Data;

@Data
public class DirectPurchaseMembers {

	private Integer id;

	private Integer regionId;

	private Integer studentId;

	private Integer coachesId;

	private String orderNum;

	private Integer createTime;

	private Integer regionPrice;

	private Integer isProtect;

	private Integer protectPrice;

	private Integer voucherPrice;

	private Integer finalPrice;

	private Integer status;

	private String payMethod;

	private Integer payTime;

	private String name;

	private String phone;

	private String updateTime;
}
