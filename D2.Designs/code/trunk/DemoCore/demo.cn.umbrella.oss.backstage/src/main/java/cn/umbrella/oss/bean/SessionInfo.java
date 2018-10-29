package cn.umbrella.oss.bean;

public class SessionInfo {
	public String myCreditEntityName; // 信用主体名称
	public String myCreditEntityCode; // 信用主体标识码（1.企业和其他组织取组织机构代码，2.个人取身份证号）
	public Integer authRange; // 认证等级
	public String myUnifySocietyCode; // 统一社会信用代码
	public Integer myCreditOwnerType; // 查询种类（1："其他组织"，2："自然人"，3："法人"）

	public String regNumber; // 工商注册号
	public String userId; // 法人在办事系统内部的登录名
	public String password; // 法人在办事系统内部的登录口令
	public String apprInfo; // 办事系统内部信息

	public String loginName;

	public String getMyCreditEntityCode() {
		return myCreditEntityCode;
	}

	public void setMyCreditEntityCode(String myCreditEntityCode) {
		this.myCreditEntityCode = myCreditEntityCode;
	}

	public Integer getAuthRange() {
		return authRange;
	}

	public void setAuthRange(Integer authRange) {
		this.authRange = authRange;
	}

	public String getMyUnifySocietyCode() {
		return myUnifySocietyCode;
	}

	public void setMyUnifySocietyCode(String myUnifySocietyCode) {
		this.myUnifySocietyCode = myUnifySocietyCode;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApprInfo() {
		return apprInfo;
	}

	public void setApprInfo(String apprInfo) {
		this.apprInfo = apprInfo;
	}

	public String getMyCreditEntityName() {
		return myCreditEntityName;
	}

	public void setMyCreditEntityName(String myCreditEntityName) {
		this.myCreditEntityName = myCreditEntityName;
	}

	public Integer getMyCreditOwnerType() {
		return myCreditOwnerType;
	}

	public void setMyCreditOwnerType(Integer myCreditOwnerType) {
		this.myCreditOwnerType = myCreditOwnerType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return "CreditSessionInfo [creditEntityName=" + myCreditEntityName
				+ ", creditEntityCode=" + myCreditEntityCode + ", authRange="
				+ authRange + ", unifySocietyCode=" + myUnifySocietyCode
				+ ", creditOwnerType=" + myCreditOwnerType + ", regNumber="
				+ regNumber + ", userId=" + userId + ", password=" + password
				+ ", apprInfo=" + apprInfo + "]";
	}

}
