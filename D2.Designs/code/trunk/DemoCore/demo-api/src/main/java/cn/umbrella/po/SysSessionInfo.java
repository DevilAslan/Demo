package cn.umbrella.po;

import java.util.List;

public class SysSessionInfo {
	//登录用户所属的组的根用户组
	public SysGroup myRootGroup;
	//登录用户所属的主帐号的角色
	public List<SysRole> myRootRoles;
	//登录主用户的自然信息
	public SysUser myRootUser;
	//登录用户的自然信息
	public SysUser myUser;
	//登录用户的账户信息
	public SysAccount myAccount;
	//登录用户的所有角色
	public List<SysRole> myRoles;
	//登录用户所属的所有组
	public List<SysGroup> myGroups;
	//登录用户拥有的所有权限
	public List<SysResource> myResources;
	//登录用户拥有的所有菜单
	public List<SysMenu> myMenus;
	//登录用户拥有的所有顶部菜单
	public List<SysMenu> myTopMenus;
	//登录用户拥有的当前所有左侧菜单
	public List<SysMenu> myLeftMenus;
	//登录用户的当顶部前菜单id
	public Integer myCurrTopMenuId;
	//登录用户的当顶左侧菜单id
	public Integer myCurrLeftMenuId;
	//登录用户的所有消息数量
	public int msgTotalNum;
	//登录用户的未读消息数量
	public int msgUnReadNum;
	//登录所属的系统Id
	public String sysId;
	//token
	public String token;
	
	public SysAccount getMyAccount() {
		return myAccount;
	}
	public void setMyAccount(SysAccount myAccount) {
		this.myAccount = myAccount;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public List<SysRole> getMyRootRoles() {
		return myRootRoles;
	}
	public void setMyRootRoles(List<SysRole> myRootRoles) {
		this.myRootRoles = myRootRoles;
	}
	public SysUser getMyRootUser() {
		return myRootUser;
	}
	public void setMyRootUser(SysUser myRootUser) {
		this.myRootUser = myRootUser;
	}
	
	public SysUser getMyUser() {
		return myUser;
	}
	public void setMyUser(SysUser myUser) {
		this.myUser = myUser;
	}
	public List<SysRole> getMyRoles() {
		return myRoles;
	}
	public void setMyRoles(List<SysRole> myRoles) {
		this.myRoles = myRoles;
	}
	public List<SysGroup> getMyGroups() {
		return myGroups;
	}
	public void setMyGroups(List<SysGroup> myGroups) {
		this.myGroups = myGroups;
	}
	public SysGroup getMyRootGroup() {
		return myRootGroup;
	}
	public void setMyRootGroup(SysGroup myRootGroup) {
		this.myRootGroup = myRootGroup;
	}
	public List<SysResource> getMyResources() {
		return myResources;
	}
	public void setMyResources(List<SysResource> myResources) {
		this.myResources = myResources;
	}
	public List<SysMenu> getMyMenus() {
		return myMenus;
	}
	public void setMyMenus(List<SysMenu> myMenus) {
		this.myMenus = myMenus;
	}
	public List<SysMenu> getMyTopMenus() {
		return myTopMenus;
	}
	public void setMyTopMenus(List<SysMenu> myTopMenus) {
		this.myTopMenus = myTopMenus;
	}
	public List<SysMenu> getMyLeftMenus() {
		return myLeftMenus;
	}
	public void setMyLeftMenus(List<SysMenu> myLeftMenus) {
		this.myLeftMenus = myLeftMenus;
	}
	public Integer getMyCurrTopMenuId() {
		return myCurrTopMenuId;
	}
	public void setMyCurrTopMenuId(Integer myCurrTopMenuId) {
		this.myCurrTopMenuId = myCurrTopMenuId;
	}
	public Integer getMyCurrLeftMenuId() {
		return myCurrLeftMenuId;
	}
	public void setMyCurrLeftMenuId(Integer myCurrLeftMenuId) {
		this.myCurrLeftMenuId = myCurrLeftMenuId;
	}
	public int getMsgTotalNum() {
		return msgTotalNum;
	}
	public void setMsgTotalNum(int msgTotalNum) {
		this.msgTotalNum = msgTotalNum;
	}
	public int getMsgUnReadNum() {
		return msgUnReadNum;
	}
	public void setMsgUnReadNum(int msgUnReadNum) {
		this.msgUnReadNum = msgUnReadNum;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}