package org.qianrenxi.core.system.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.repository.jpa.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleShiroRealm extends AuthorizingRealm {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleShiroRealm.class);

	@Autowired
	private UserRepository userRepository;
	// private UserService userService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//String username = (String) getAvailablePrincipal(principalCollection);
		User user = (User) getAvailablePrincipal(principalCollection);
		logger.info(String.format("用户[%s]权限认证...", user.getUsername()));
		
		//User user = userRepository.findByUsername(username);
		if(user!=null){
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addRole("admin");
			// TODO: 组织权限信息
			return authorizationInfo;
		}
		
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		logger.info(String.format("用户[%s]登录...", token.getUsername()));
		
		User user = userRepository.findByUsername(token.getUsername());
		if(user!=null){
			// return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
			UserToken userToken = new UserToken(user);
			userToken.setCurrentSite(user.getSite());
			return new SimpleAuthenticationInfo(userToken, user.getPassword(), getName());
		}
		return null;
	}

	@Override
	public void checkPermission(PrincipalCollection principal, Permission permission) throws AuthorizationException {
		// TODO Auto-generated method stub
		super.checkPermission(principal, permission);
	}
}
