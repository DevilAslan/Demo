package cn.umbrella.commons.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Aslan
 */
public class ResultModel<T> implements Serializable {

    private static final long serialVersionUID = 5119603949028422916L;

    public static final String SUCCESS = "ok";
    public static final String ERROR = "error";
    public static final String FAILED = "failed";

    private String ret;

    private String errordesc;

    private Integer errorcode;

    private T data;

    private ResultModel(String ret, String errordesc, Integer errorcode, T data) {
        this.ret = ret;
        this.errordesc = errordesc;
        this.errorcode = errorcode;
        this.data = data;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> success() {
        return new ResultModel(SUCCESS, StringUtils.EMPTY, 0, null);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> success(Object data) {
        return new ResultModel(SUCCESS, StringUtils.EMPTY, 0, data);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> success(String errordesc) {
        return new ResultModel(SUCCESS, errordesc, 0, null);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> success(String errordesc, Object data) {
        return new ResultModel(SUCCESS, errordesc, 0, data);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> error(Integer errorcode) {
        return new ResultModel(ERROR, StringUtils.EMPTY, errorcode, null);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static ResultModel<?> error(Integer errorcode, Object data) {
        return new ResultModel(ERROR, StringUtils.EMPTY, errorcode, data);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResultModel<?> error(String message, Integer errorcode) {
        return new ResultModel(ERROR, message, errorcode, null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResultModel<?> error(String message, Integer errorcode, Object data) {
        return new ResultModel(ERROR, message, errorcode, data);
    }

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getErrordesc() {
		return errordesc;
	}

	public void setErrordesc(String errordesc) {
		this.errordesc = errordesc;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
