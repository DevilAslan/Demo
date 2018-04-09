package cn.umbrella.commons.enums;

public enum IsDeleted {
	
	UNDELETED(0,"未删除"),
	DELETED(1,"已删除");
	
	private final int value;
    private final String name;
    
    private IsDeleted(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
