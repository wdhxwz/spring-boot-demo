package com.spring.boot.demo.apirouter;

/**
 * 接口调用异常
 * @author wdhcxx
 *
 */
public class ApiException extends RuntimeException{
	private static final long serialVersionUID = 979784207317684201L;

	public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }
}