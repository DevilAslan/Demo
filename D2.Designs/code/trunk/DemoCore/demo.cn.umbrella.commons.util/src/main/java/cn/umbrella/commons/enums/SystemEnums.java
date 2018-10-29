package cn.umbrella.commons.enums;

public interface SystemEnums {

	/**
	 * 功能枚举
	 *
	 */
	public enum FunctionType{
		/**
		 * 系统菜单
		 */
		MENU(0,"系统菜单"),
		/**
		 * 操作功能
		 */
		FUNC(1,"操作功能");
		private int value;
		private String desc;
		
		private FunctionType(int value, String desc){
			this.value = value;
			this.desc = desc;
		}
		public int getValue() {
			return value;
		}
		public String getDesc() {
			return desc;
		}
	}
	
}
