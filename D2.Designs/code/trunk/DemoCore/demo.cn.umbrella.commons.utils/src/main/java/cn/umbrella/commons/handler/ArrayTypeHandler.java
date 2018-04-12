package cn.umbrella.commons.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * 
 * 自定义mybatis和model映射  
 *
 * @ClassName: ArrayTypeHandler  
 * @author zhou.xy
 * @date 2016年1月20日 下午2:35:49  
 *
 */
@MappedTypes(String[].class)
@Alias(value="String[]")
public class ArrayTypeHandler implements TypeHandler<String[]> {

	/**
	 * 数据库操作的时候调用：新增，修改
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setNull(i, Types.VARCHAR);
		} else {
			ps.setString(i, StringUtils.join(parameter, ","));
		}
	}

	/**
	 * 查询时调用
	 */
	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		if(columnName == null) {
			return new String[0];
		} else {
			return rs.getString(columnName).split(",");
		}
	}

	/**
	 * 查询时调用
	 */
	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		return rs.getString(columnIndex).split(",");
	}

	/**
	 * 查询时调用
	 */
	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return cs.getString(columnIndex).split(",");
	}

}
