package org.qianrenxi.core.upload.exception;

import org.qianrenxi.core.common.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class FileUploadException extends BusinessException {
	private static final long serialVersionUID = -1351524603475045914L;

	public FileUploadException() {
		super();
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
	}
	public FileUploadException(String messageKey, Object[] args) {
		super(messageKey, args);
	}
	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(Throwable cause) {
		super(cause);
	}

	protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
