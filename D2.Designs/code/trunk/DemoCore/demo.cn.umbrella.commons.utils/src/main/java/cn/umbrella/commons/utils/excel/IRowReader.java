package cn.umbrella.commons.utils.excel;

import java.util.List;

import org.xml.sax.SAXException;

public interface IRowReader {

	/**业务逻辑实现方法
	 * @param sheetIndex
	 * @param curRow
	 * @param rowlist
	 */
	public void getRows(int sheetIndex, int curRow, List<String> rowlist) throws SAXException;
}

