package org.qianrenxi.core.system.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.qianrenxi.core.system.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//return super.onAccessDenied(request, response);
		if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                //if (log.isTraceEnabled()) {
                    log.info("Login submission detected.  Attempting to execute login.");
                //}
                return executeLogin(request, response);
            } else {
                //if (log.isTraceEnabled()) {
                    log.info("Login page view.");
                //}
                //allow them to see the login page ;)
                return true;
            }
        } else {
            //if (log.isTraceEnabled()) {
                log.info("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            //}

            saveRequestAndRedirectToLogin(request, response);
            // TODO: 两种思路：1、直接在修改次方法返回json{unauthentication..} / redirectToLogin
            // 2. 修改redirectToLogin方法的getLoginPath方法，当为text/html时跳转到login，否则跳转到一个返回json对象的UnAuthentication Controller
            // 3. 直接修改 /login controller，判断请求类型，为text/html时返回页面，否则抛出 UNAuthentication 异常
            // 第一种方法直接返回了JSON；第二种方法都会做跳转，但跳转的controller不一样；
            // 第三种方法不需要改默认的FormAuthenticationFilter，对 /login Controller 稍微做下改动即可
            return false;
        }
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		subject.getSession().setAttribute(Constants.SESSION_KEY, subject.getPrincipal());
		return super.onLoginSuccess(token, subject, request, response);
	}
}
