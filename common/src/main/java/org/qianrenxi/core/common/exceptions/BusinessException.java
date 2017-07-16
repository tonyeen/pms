package org.qianrenxi.core.common.exceptions;

import org.qianrenxi.core.common.spring.SpringUtil;
import org.qianrenxi.core.i18n.LocaleMessageSourceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 基础业务异常类，通过增加成员变量 messageKey 和 args 并重写父类的 getMessage() 方法对国际化进行支持
 * @author jiawei
 *
 */
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/**
	 * 国际化参数名
	 */
	protected String messageKey;
	/**
	 * 占位符形式对应的参数值
	 */
	protected Object[] args;
	protected LocaleMessageSourceService messageSourceService = SpringUtil.getBean(LocaleMessageSourceService.class);
	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	public BusinessException(String messageKey, Object[] args) {
		super();
		this.messageKey = messageKey;
		this.args = args;
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getLocalizedMessage() {
        return getMessage();
    }
	@Override
    public String getMessage() {
	    String defaultMessage = messageSourceService.getMessage(super.getMessage(),super.getMessage());
        return messageSourceService.getMessage(messageKey, args, defaultMessage);
    }
	
}
