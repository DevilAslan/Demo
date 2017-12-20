package cn.umbrella.commons.util.usual;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

/**
 * 綁定對象
 * 
 */
public final class BeanUtil {
	static protected final Log log = LogFactory.getLog(BeanUtil.class
			.getName());

	/**
	 * Array to Collection
	 * 
	 * @param rs
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public static Collection<Object> bind(String[][] rs, Object object)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			if (rs != null && rs.length > 1) {
				String[] propertyNameArr = new String[rs[0].length];
				for (int i = 0; i < propertyNameArr.length; i++) {
					propertyNameArr[i] = getPropertyName(rs[0][i]);
				}
				for (int i = 1; i < rs.length; i++) {
					BeanWrapper wapper = new BeanWrapperImpl(Class.forName(
							getClassName(object)).newInstance());
					for (int j = 0; j < rs[i].length; j++) {
						wapper.setPropertyValue(propertyNameArr[j], rs[i][j]);
					}
					list.add(wapper.getWrappedInstance());
				}
			}
		} catch (ClassNotFoundException e) {
			log.error(e);
			throw e;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return list;

	}

	/**
	 * Array to Object
	 * 
	 * @param rs
	 * @param className
	 * @return
	 */
	public static Object bindObject(String[][] rs, Object object)
			throws Exception {
		Object retObject = null;
		try {
			if (rs != null && rs.length > 1) {
				BeanWrapper wapper = new BeanWrapperImpl(Class.forName(
						getClassName(object)).newInstance());
				for (int j = 0; j < rs[1].length; j++) {
					wapper
							.setPropertyValue(getPropertyName(rs[0][j]),
									rs[1][j]);
				}
				retObject = wapper.getWrappedInstance();
			}
		} catch (ClassNotFoundException e) {
			log.error(e);
			throw e;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return retObject;
	}

	/**
	 * ResultSet to Collection
	 * 
	 * @param rs
	 * @param object
	 * @return
	 */
	public static Collection<Object> bind(ResultSet rs, Object object)
			throws Exception {
		return bind(rs, getClassName(object));

	}

	/**
	 * 
	 * @param rs
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Object bindObject(ResultSet rs, Object object)
			throws Exception {
		return bindObject(rs, getClassName(object));
	}

	/**
	 * ResultSet to Collection
	 * 
	 * @param rs
	 * @param className
	 * @return
	 */
	public static Collection<Object> bind(ResultSet rs, String className)
			throws Exception {
		return bindList(rs, className);
	}

	/**
	 * ResultSet to Collection
	 * 
	 * @param rs
	 * @param className
	 * @return
	 */
	public static List<Object> bindList(ResultSet rs, String className)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		ResultSetMetaData rsmd = null;
		try {
			if (rs != null) {
				if (!isBaseType(className)) {
					/** 不是基本的數據類型 */

					int iCol = 0;
					rsmd = rs.getMetaData();
					iCol = rsmd.getColumnCount();
					String[] propertyNameArr = new String[iCol];
					for (int i = 0; i < iCol; i++) {
						propertyNameArr[i] = getPropertyName(rsmd
								.getColumnName(i + 1).trim());
					}
					while (rs.next()) {
						BeanWrapper wapper = new BeanWrapperImpl(Class.forName(
								className).newInstance());
						for (int i = 0; i < iCol; i++) {
							wapper.setPropertyValue(propertyNameArr[i], rs
									.getObject(i + 1));
						}
						list.add(wapper.getWrappedInstance());
					}
				} else {
					/** 是基本的數據類型 */
					while (rs.next()) {
						list.add(rs.getObject(1));
					}
				}
			}
			rsmd = null;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (SQLException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return list;

	}

	/**
	 * ResultSet to Object
	 * 
	 * @param rs
	 * @param className
	 * @return
	 */
	public static Object bindObject(ResultSet rs, String className)
			throws Exception {
		ResultSetMetaData rsmd = null;
		BeanWrapper wapper = null;
		Object retObject = null;
		try {
			if (rs != null) {
				if (!isBaseType(className)) {
					/** 不是基本的數據類型 */

					int iCol = 0;
					String name = "";
					Object value = "";
					rsmd = rs.getMetaData();
					iCol = rsmd.getColumnCount();
					if (rs.next()) {
						wapper = new BeanWrapperImpl(Class.forName(className)
								.newInstance());
						for (int i = 0; i < iCol; i++) {
							name = rsmd.getColumnName(i + 1).trim();
							value = rs.getObject(i + 1);
							wapper.setPropertyValue(getPropertyName(name),
									value);
						}
						retObject = wapper.getWrappedInstance();
					}
				} else {
					/** 是基本的數據類型 */
					if (rs.next()) {
						retObject = rs.getObject(1);
					}
				}
			}
			rsmd = null;
		} catch (ClassNotFoundException e) {
			log.error(e);
			throw e;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (SQLException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return retObject;

	}

	/**
	 * 取得class名
	 * 
	 * @param object
	 * @return
	 */
	public static String getClassName(Object object) {
		// object.toString = Class com.ccc.util.BeanUtil
		return object.toString().substring(object.toString().indexOf(" ") + 1);
	}

	/**
	 * 根據db column name 得到 JavaBean的屬性名
	 * 
	 * @param dbColumnName
	 * @return
	 */
	private static String getPropertyName(String dbColumnName) {
		if (dbColumnName == null || dbColumnName.length() == 0) {
			return "";
		}
		dbColumnName = dbColumnName.toLowerCase();
		StringBuffer stringbuffer = new StringBuffer();
		String regex = "_";
		String addString = "";
		String[] sbArr = dbColumnName.split(regex);
		// 當隻有一個字母時,就是這個字母的小寫
		if (sbArr.length == 1 && sbArr[0].length() == 1) {
			stringbuffer.append(sbArr[0]);
		} else {
			for (int i = 0; i < sbArr.length; i++) {
				if (i == 0) {
					if (sbArr[i].length() == 1) {
						// 當隻有一個字母開頭時,大寫第一個
						addString = sbArr[i].toUpperCase();
					} else {
						addString = sbArr[i];
					}
				} else {
					if (sbArr[i].length() == 1) {
						// 當隻有一個字母開頭時,大寫第一個
						addString = sbArr[i].toUpperCase();
					} else {
						addString = sbArr[i].substring(0, 1).toUpperCase()
								+ sbArr[i].substring(1, sbArr[i].length());
					}
				}
				stringbuffer.append(addString);
			}
		}
		return stringbuffer.toString();
	}

	/**
	 * jdbcTemplate結果轉換成LIST
	 * <p>
	 * 
	 * @author F1643317
	 * @date Dec 14, 2009 3:36:50 PM
	 * @param param1
	 *            type 描述
	 * @param param2
	 *            type 描述
	 *            <ul>
	 *            <li>value1描述 <li>value2描述
	 *            </ul>
	 * @see package.className[#method([param])]
	 * @throws exceptionType
	 *             如果...將拋出exceptionType異常.
	 * @return returnType 返回類型描述
	 */
	public static List<Object> bindList(SqlRowSet rs, String className)
			throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		SqlRowSetMetaData rsmd = null;
		try {
			if (rs != null) {
				if (!isBaseType(className)) {
					/** 不是基本的數據類型 */

					int iCol = 0;
					rsmd = rs.getMetaData();
					iCol = rsmd.getColumnCount();
					String[] propertyNameArr = new String[iCol];
					for (int i = 0; i < iCol; i++) {
						propertyNameArr[i] = getPropertyName(rsmd
								.getColumnName(i + 1).trim());
					}
					while (rs.next()) {
						BeanWrapper wapper = new BeanWrapperImpl(Class.forName(
								className).newInstance());
						for (int i = 0; i < iCol; i++) {
							wapper.setPropertyValue(propertyNameArr[i], rs
									.getObject(i + 1));
						}
						list.add(wapper.getWrappedInstance());
					}
				} else {
					/** 是基本的數據類型 */
					while (rs.next()) {
						list.add(rs.getObject(1));
					}
				}
			}
			rsmd = null;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return list;

	}

	public static Object bindObject(SqlRowSet rs, String className)
			throws Exception {
		Object retObject = null;
		SqlRowSetMetaData rsmd = null;
		try {
			if (rs != null) {
				if (!isBaseType(String.valueOf(className))) {
					/** 不是基本的數據類型 */

					int iCol = 0;
					rsmd = rs.getMetaData();
					iCol = rsmd.getColumnCount();
					String[] propertyNameArr = new String[iCol];
					for (int i = 0; i < iCol; i++) {
						propertyNameArr[i] = getPropertyName(rsmd
								.getColumnName(i + 1).trim());
					}
					if (rs.next()) {
						BeanWrapper wapper = new BeanWrapperImpl(Class.forName(
								getClassName(className)).newInstance());
						for (int i = 0; i < iCol; i++) {
							wapper.setPropertyValue(propertyNameArr[i], rs
									.getObject(i + 1));
						}
						retObject = wapper.getWrappedInstance();
					}
				} else {
					/** 是基本的數據類型 */
					if (rs.next()) {
						retObject = rs.getObject(1);
					}
				}
			}
			rsmd = null;
		} catch (IllegalAccessException e) {
			log.error(e);
			throw e;
		} catch (InstantiationException e) {
			log.error(e);
			throw e;
		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}
		return retObject;

	}

	/**
	 * 判斷是否是基本的數據類型
	 * <p>
	 * 
	 * @author F1041726
	 * @date Dec 9, 2010 4:50:23 PM
	 */
	public static boolean isBaseType(String typeName) {
		boolean baseFlag = false;
		String[] baseTypeArr = { "java.lang.Byte", "java.lang.Short",
				"java.lang.Integer", "java.lang.Long", "java.lang.Float",
				"java.lang.Double", "java.lang.Character", "java.lang.Boolean",
				"java.lang.String", "java.util.Date" };
		for (int i = 0; i < baseTypeArr.length; i++) {
			if (typeName.equals(baseTypeArr[i])) {
				baseFlag = true;
				break;
			}
		}
		return baseFlag;
	}

	public static void main(String[] args) {
		String name = getPropertyName("a");
		System.out.println(name);
		name = getPropertyName("mdd_fdf_time");
		// System.out.println(name);
	}
}