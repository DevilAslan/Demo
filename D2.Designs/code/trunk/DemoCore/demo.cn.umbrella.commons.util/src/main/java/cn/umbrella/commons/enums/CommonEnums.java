package cn.umbrella.commons.enums;


public interface CommonEnums {
	
	/**
	 * 布尔枚举常量
	 */
	public enum Boolflag{
		/**
		 * 否
		 */
		FALSE(0,"否"),
		/**
		 * 是
		 */
		TRUE(1,"是");
		private int value;
		private String desc;
		
		private Boolflag(int value, String desc){
			this.value = value;
			this.desc = desc;
		}
		
		public int getValue(){
			return value;
		}
		
		public String getDesc(){
			return desc;
		}
	}
	
	/**
	 * 状态枚举常量
	 */
	public enum CommonStatu{
		
		/**
		 * 编辑
		 */
		COMPILE(0,"编辑"),
		/**
		 * 启用
		 */
		ENABLE(1,"启用"),
		/**
		 * 停用
		 */
		DISABLE(2,"停用");
		private int value;
		private String desc;
		
		private CommonStatu(int value, String desc){
			this.value = value;
			this.desc = desc;
		}
		
		public int getValue(){
			return value;
		}
		
		public String getDesc(){
			return desc;
		}
	}
	
}
