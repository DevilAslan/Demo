package cn.umbrella.commons.model;

public class Column {

	private String columnName = "";
	private String columnType = "";
	private String explain = "";
	private boolean required;
	private String remarks = "";

	public Column(String columnName, String columnType) {
		super();
		this.columnName = columnName;
		this.columnType = columnType;
	}

	public Column(String columnName, String columnType, boolean required) {
		super();
		this.columnName = columnName;
		this.columnType = columnType;
		this.required = required;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "[" + columnName + ", " + columnType + ", " + explain + ", "
				+ required + ", " + remarks + "]";
	}

}
