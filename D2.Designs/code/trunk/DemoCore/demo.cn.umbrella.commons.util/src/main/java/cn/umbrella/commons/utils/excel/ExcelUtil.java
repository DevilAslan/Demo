package cn.umbrella.commons.utils.excel;

import cn.umbrella.commons.utils.excel.bean.IExcelExport;
import cn.umbrella.commons.utils.excel.bean.IExcelImport;
import cn.umbrella.commons.utils.excel.bean.impl.SimpleExcelExport;
import cn.umbrella.commons.utils.excel.bean.impl.SimpleExcelImport;

/**
 * 导出工具类
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月29日
 */
public class ExcelUtil {
	public final static int EXP_MODE_ANN = 1;// 注解
	public final static int EXP_MODE_OBJ = 2;// 对象 TODO
	public final static int EXP_MODE_MAP = 3;// 集合 TODO

	public static IExcelImport getImport() {
		return new SimpleExcelImport();
	}

	public static IExcelExport getExport() {
		return getExport(EXP_MODE_ANN);
	}

	public static IExcelExport getExport(int mode) {
		IExcelExport export = null;
		switch (mode) {
		case ExcelUtil.EXP_MODE_ANN:
			export = new SimpleExcelExport();
			break;
		case EXP_MODE_OBJ:
			break;
		case EXP_MODE_MAP:
			break;
		default:
			break;
		}

		return export;
	}
}
