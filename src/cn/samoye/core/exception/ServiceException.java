package cn.samoye.core.exception;

public class ServiceException extends SysException{

	public ServiceException() {
		super("service 出现错误");
	}

	public ServiceException(String message) {
		super(message);
	}

}
