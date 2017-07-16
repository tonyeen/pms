package org.qianrenxi.core.system.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.security.UserToken;
import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<User> {

	@Override
	public User getCurrentAuditor() {
		Object principal = SecurityUtils.getSubject().getPrincipal();
		if(null != principal && principal instanceof UserToken){
			return ((UserToken) principal).getUser();
		}else {
			throw new UnauthenticatedException();
		}
	}
	
}
