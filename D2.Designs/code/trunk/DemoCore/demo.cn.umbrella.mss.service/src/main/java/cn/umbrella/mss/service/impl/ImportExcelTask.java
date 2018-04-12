package cn.umbrella.mss.service.impl;

import java.util.UUID;

import org.apache.poi.ss.usermodel.Sheet;
/**
 * Type Name : ImportExcelTask
 * Description : 处理导入Excel 
 * Author : 林吉前
 * Create Date ： 2017年6月29日
 * Version ：
 *
 * History: (Version) Author Date Annotation
 */
public class ImportExcelTask extends cn.umbrella.commons.masterworker.Task{

	private Sheet sheet;
	private int index;
	
	public ImportExcelTask(Sheet sheet,int index){
		super(UUID.randomUUID().toString());
		this.sheet = sheet;
		this.index = index;
	}

	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
