package org.qianrenxi.core.system.shiro.inteceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.Subject.Builder;
import org.apache.shiro.util.ThreadContext;
import org.qianrenxi.core.common.spring.SpringUtil;
import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.security.UserToken;
import org.qianrenxi.core.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ShiroTestInterceptor extends   HandlerInterceptorAdapter  {

	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
            Object handler) throws Exception {
         // 验证用户是否登陆
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal == null || !(principal instanceof UserToken)) {
        	UserService userService = SpringUtil.getBean(UserService.class);
        	User user = userService.findByUsername("system");
        	UserToken userToken = new UserToken(user);
    		userToken.setCurrentSite(user.getSite());
    		Builder builder = new Subject.Builder(SecurityUtils.getSecurityManager());  
    		PrincipalCollection principals = new SimplePrincipalCollection(userToken,user.getUsername() );
    		builder.authenticated(true);
    		builder.principals(principals);
    		Subject buildSubject = builder.buildSubject();
    		ThreadContext.bind(buildSubject);
        }
        return true;
    }
}
