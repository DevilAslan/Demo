package cn.umbrella.vo;

public class CreditSessionInfo {
	public String creditName; // 信用主体名称
	public String creditCode; // 信用主体标识码（1.企业和其他组织取组织机构代码，2.个人取身份证号）
	public String authRange; // 认证等级
	public String unifySocietyCode; // 统一社会信用代码
	public String creditType; // 查询种类（1、个人,2、企业）

	public String regNumber; // 工商注册号
	public String userId; // 法人在办事系统内部的登录名
	public String password; // 法人在办事系统内部的登录口令
	public String apprInfo; // 办事系统内部信息

	public String loginname;// 登录名称
	public String mobile; // 手机号码
	public String email; // 邮箱

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getAuthRange() {
		return authRange;
	}

	public void setAuthRange(String authRange) {
		this.authRange = authRange;
	}

	public String getUnifySocietyCode() {
		return unifySocietyCode;
	}

	public void setUnifySocietyCode(String unifySocietyCode) {
		this.unifySocietyCode = unifySocietyCode;
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		if (null != loginname) {
			loginname = loginname.trim();
		}
		this.loginname = loginname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CreditSessionInfo [creditName=" + creditName + ", creditCode="
				+ creditCode + ", authRange=" + authRange
				+ ", unifySocietyCode=" + unifySocietyCode + ", creditType="
				+ creditType + ", regNumber=" + regNumber + ", userId="
				+ userId + ", password=" + password + ", apprInfo=" + apprInfo
				+ "]";
	}

}
