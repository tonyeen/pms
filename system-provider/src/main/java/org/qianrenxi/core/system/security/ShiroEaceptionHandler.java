package org.qianrenxi.core.system.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShiroEaceptionHandler {
	
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="未认证...")
	@ExceptionHandler(UnauthorizedException.class)
	public void handleUnauthorizedException(HttpServletRequest request, UnauthorizedException e){
		
	}
}
