package org.qianrenxi.core.common.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class GlobalExceptionHandler extends ExceptionHandlerExceptionResolver {
	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private final static String HTTP_REQUEST_HEADER_NAME_CONTENT_TYPE = "content-type";
	private final static String HTTP_REQUEST_HEADER_CONTENT_TYPE_JSON = "json";
	private final static String HTTP_RESPONSE_ERROR_INFO = "好像这里是错了：%s";
	private final static String HTTP_RESPONSE_HEADER_INFO = "请求头信息：%s";
	
	/*@ExceptionHandler(value = MyException.class)
	public Object baseErrorHandler(HttpServletRequest request, Exception e) {
		if(isJson(request)){
			return ResponseUtils.restResponse(
                    e.getCode(),
                    e.getMessage(),
                    e.getStatus()
            );
			ResponseEntity<Object> re = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			return re;
		}
		logger.info(String.format(HTTP_RESPONSE_ERROR_INFO, e.getMessage()));
		return e.getMessage();
	}*/
	
	private boolean isJson(HttpServletRequest request){
		boolean isJson_ = !(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1));
		logger.info(String.format("是否是JSON请求：%b", isJson_));
		
		String header = request.getHeader(HTTP_REQUEST_HEADER_NAME_CONTENT_TYPE);
		Enumeration<String> headerNames = request.getHeaderNames();
		logger.info(String.format(HTTP_RESPONSE_HEADER_INFO, headerNames.toString()));
		return header != null && header.contains(HTTP_REQUEST_HEADER_CONTENT_TYPE_JSON);
	}
}
