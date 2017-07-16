package org.qianrenxi.core.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class SimpleException extends BusinessException {
	private static final long serialVersionUID = -1351524603475045914L;

	public SimpleException() {
		super();
	}

	public SimpleException(String message, Throwable cause) {
		super(message, cause);
	}
	public SimpleException(String messageKey, Object[] args) {
		super(messageKey, args);
	}

	public SimpleException(String message) {
		super(message);
	}

	public SimpleException(Throwable cause) {
		super(cause);
	}

	protected SimpleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
