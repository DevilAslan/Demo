package cn.umbrella.finance.mapper.alipay;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.umbrella.finance.dataobject.alipay.AlipayRefundNo;
import cn.umbrella.finance.mapper.alipay.AlipayRefundNoSqlProvider;

public interface AlipayRefundNoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @Delete({
        "delete from alipay_refund_no",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @Insert({
        "insert into alipay_refund_no (batch_no, batch_num, ",
        "out_trade_no, trade_no, ",
        "refund_fee, refund_date, ",
        "refund_status, create_time, ",
        "update_time)",
        "values (#{batchNo,jdbcType=VARCHAR}, #{batchNum,jdbcType=VARCHAR}, ",
        "#{outTradeNo,jdbcType=VARCHAR}, #{tradeNo,jdbcType=VARCHAR}, ",
        "#{refundFee,jdbcType=VARCHAR}, #{refundDate,jdbcType=VARCHAR}, ",
        "#{refundStatus,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AlipayRefundNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @InsertProvider(type=AlipayRefundNoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AlipayRefundNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @Select({
        "select",
        "id, batch_no, batch_num, out_trade_no, trade_no, refund_fee, refund_date, refund_status, ",
        "create_time, update_time",
        "from alipay_refund_no",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="batch_num", property="batchNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="out_trade_no", property="outTradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_no", property="tradeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_fee", property="refundFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_date", property="refundDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_status", property="refundStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AlipayRefundNo selectByPrimaryKey(Integer id);
    
    @Select({
    	"select",
    	"id, batch_no, batch_num, out_trade_no, trade_no, refund_fee, refund_date, refund_status, ",
    	"create_time, update_time",
    	"from alipay_refund_no",
    	"where batch_no = #{batch_no,jdbcType=INTEGER}"
    })
    @Results({
    	@Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
    	@Result(column="batch_no", property="batchNo", jdbcType=JdbcType.VARCHAR),
    	@Result(column="batch_num", property="batchNum", jdbcType=JdbcType.VARCHAR),
    	@Result(column="out_trade_no", property="outTradeNo", jdbcType=JdbcType.VARCHAR),
    	@Result(column="trade_no", property="tradeNo", jdbcType=JdbcType.VARCHAR),
    	@Result(column="refund_fee", property="refundFee", jdbcType=JdbcType.VARCHAR),
    	@Result(column="refund_date", property="refundDate", jdbcType=JdbcType.VARCHAR),
    	@Result(column="refund_status", property="refundStatus", jdbcType=JdbcType.VARCHAR),
    	@Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
    	@Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AlipayRefundNo selectByBatchNo(String batch_no);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @UpdateProvider(type=AlipayRefundNoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AlipayRefundNo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    @Update({
        "update alipay_refund_no",
        "set batch_no = #{batchNo,jdbcType=VARCHAR},",
          "batch_num = #{batchNum,jdbcType=VARCHAR},",
          "out_trade_no = #{outTradeNo,jdbcType=VARCHAR},",
          "trade_no = #{tradeNo,jdbcType=VARCHAR},",
          "refund_fee = #{refundFee,jdbcType=VARCHAR},",
          "refund_date = #{refundDate,jdbcType=VARCHAR},",
          "refund_status = #{refundStatus,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AlipayRefundNo record);
}