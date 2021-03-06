package cn.umbrella.finance.mapper.alipay;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;
import cn.umbrella.finance.dataobject.alipay.AlipayRefundNo;

public class AlipayRefundNoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    public String insertSelective(AlipayRefundNo record) {
        BEGIN();
        INSERT_INTO("alipay_refund_no");
        
        if (record.getBatchNo() != null) {
            VALUES("batch_no", "#{batchNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBatchNum() != null) {
            VALUES("batch_num", "#{batchNum,jdbcType=VARCHAR}");
        }
        
        if (record.getOutTradeNo() != null) {
            VALUES("out_trade_no", "#{outTradeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeNo() != null) {
            VALUES("trade_no", "#{tradeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundFee() != null) {
            VALUES("refund_fee", "#{refundFee,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundDate() != null) {
            VALUES("refund_date", "#{refundDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundStatus() != null) {
            VALUES("refund_status", "#{refundStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table alipay_refund_no
     *
     * @mbggenerated Mon Apr 11 16:57:04 CST 2016
     */
    public String updateByPrimaryKeySelective(AlipayRefundNo record) {
        BEGIN();
        UPDATE("alipay_refund_no");
        
        if (record.getBatchNum() != null) {
            SET("batch_num = #{batchNum,jdbcType=VARCHAR}");
        }
        
        if (record.getOutTradeNo() != null) {
            SET("out_trade_no = #{outTradeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeNo() != null) {
            SET("trade_no = #{tradeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundFee() != null) {
            SET("refund_fee = #{refundFee,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundDate() != null) {
            SET("refund_date = #{refundDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundStatus() != null) {
            SET("refund_status = #{refundStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        WHERE("batch_no = #{batchNo,jdbcType=INTEGER}");
        
        return SQL();
    }
}