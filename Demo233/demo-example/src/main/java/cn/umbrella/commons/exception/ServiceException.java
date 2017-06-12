package cn.umbrella.commons.exception;

/**
 * Created by Lee.
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2732678720685030680L;

	public ServiceException(String message) {
        super(message);
    }

}
