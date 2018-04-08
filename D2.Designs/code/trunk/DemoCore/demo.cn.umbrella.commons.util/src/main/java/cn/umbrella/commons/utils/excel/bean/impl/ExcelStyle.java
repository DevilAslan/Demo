package cn.umbrella.commons.utils.excel.bean.impl;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 样式的简单封装
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月29日
 */
public class ExcelStyle {
	//颜色映射(为了不让其他包依赖POI，做下牺牲)
	/**
	 * 颜色值 可以参考
	 * http://blog.csdn.net/for_china2012/article/details/29844661
	 */
	public final static short RED = HSSFColor.RED.index;//为了不让其他包以来
	public final static short GREEN = HSSFColor.GREEN.index;
	public final static short BLUE = HSSFColor.BLUE.index;
	
	public static CellStyle style;
	// 字体颜色
	private short color;
	// 字体大小
	private short fontSize;
	// 字体
	private String fontName;
	// 加粗
	private boolean bold;
	// 背景颜色
	private short backgroundColor;
	// 水平对齐方式
	private short align;
	// 垂直对齐方
	private short vAlign;
	// 边框宽度 FIXME 目前不能用的
	private short borderWidth;
	// 边框样式()
	private short borderStyle;
	// 边框颜色
	private short borderColor;

	public Short getColor() {
		return color;
	}

	/**
	 * 请参照 RED 属性进行增加属性设置
	 * 
	 * @param color
	 */
	public void setColor(Short color) {
		this.color = color;
	}

	public short getFontSize() {
		return fontSize;
	}

	public void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public short getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * 请参照 RED 属性进行增加属性设置
	 * 
	 * @param backgroundColor
	 */

	public void setBackgroundColor(short backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public short getAlign() {
		return align;
	}

	/**
	 * 取值 ExcelStyle.style.ALIGN_*
	 * 
	 * @param align
	 */
	public void setAlign(short align) {
		this.align = align;
	}

	public short getvAlign() {
		return vAlign;
	}

	/**
	 * 取值 ExcelStyle.style.VERTICAL_
	 * 
	 * @param vAlign
	 */
	public void setvAlign(short vAlign) {
		this.vAlign = vAlign;
	}

	public void setBorder(short borderWidth, short borderStyle,
			short borderColor) {
		this.borderWidth = borderWidth;
		this.borderStyle = borderStyle;
		this.borderColor = borderColor;
	}

	public short getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(short borderWidth) {
		this.borderWidth = borderWidth;
	}

	public short getBorderStyle() {
		return borderStyle;
	}
	/**
	 * 取值 ExcelStyle.style.BORDER_
	 * 
	 * @return
	 */
	public void setBorderStyle(short borderStyle) {
		this.borderStyle = borderStyle;
	}

	public short getBorderColor() {
		return borderColor;
	}

	/**
	 * 请参照 RED 属性进行增加属性设置
	 * 
	 * @param borderColor
	 */
	public void setBorderColor(short borderColor) {
		this.borderColor = borderColor;
	}

	protected CellStyle getPOIFont(Workbook wb) {
		CellStyle cs = wb.createCellStyle();
		Font font = wb.createFont();
		cs.setFont(font);
		if (this.color != 0) {
			font.setColor(color);
		}
		if (this.fontSize != 0) {
			font.setFontHeightInPoints(this.fontSize);
		}
		if (isNotEmtype(this.fontName)) {
			font.setFontName(this.fontName);
		}
		if (this.bold) {
			font.setBold(true);
		}
		if (this.backgroundColor != 0) {
			cs.setFillBackgroundColor(this.backgroundColor);
		}
		if (this.align != 0) {
			cs.setAlignment(align);
		}
		if (this.vAlign != 0) {
			cs.setVerticalAlignment(vAlign);
		}
		if (this.borderWidth != 0) {
			// 没有先关设置？
		}
		if (this.borderStyle != 0) {
			cs.setBorderTop(borderStyle);
			cs.setBorderBottom(borderStyle);
			cs.setBorderLeft(borderStyle);
			cs.setBorderRight(borderStyle);
		}
		if (this.borderColor != 0) {
			cs.setTopBorderColor(color);
			cs.setBottomBorderColor(color);
			cs.setLeftBorderColor(color);
			cs.setRightBorderColor(color);
		}

		return cs;
	}

	private boolean isNotEmtype(String str) {
		return !(str == null || str.trim().length() == 0);
	}
}
