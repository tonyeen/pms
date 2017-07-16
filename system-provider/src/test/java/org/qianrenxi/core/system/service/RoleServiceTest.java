package org.qianrenxi.core.system.service;

import javax.transaction.Transactional;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.Subject.Builder;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.qianrenxi.PmsApplication;
import org.qianrenxi.core.system.config.SystemConfig;
import org.qianrenxi.core.system.enity.Role;
import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.security.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PmsApplication.class, SystemConfig.class })
@TestPropertySource(locations = { "classpath:application.properties" })
@Transactional
@Rollback(true)
public class RoleServiceTest {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	protected org.apache.shiro.mgt.SecurityManager securityManager;
	
	
	@Before
	public void setUp() {
		
		User user = userService.getOne(2L);
		UserToken userToken = new UserToken(user);
		userToken.setCurrentSite(user.getSite());
		//模拟user登录
		ThreadContext.bind(securityManager);
		Builder builder = new Subject.Builder(securityManager);  
		PrincipalCollection principals = new SimplePrincipalCollection(userToken,user.getUsername());
		builder.authenticated(true);
		builder.principals(principals);
		Subject buildSubject = builder.buildSubject();
		ThreadContext.bind(buildSubject);
		
		
	}
	
	@Test
	public void testGetOne(){
		Role one = roleService.getOne(1L);
		System.out.println(one.getName());
	}
	
	@Test
	public void testSave(){
		Role role = new Role();
		role.setName("Role2");
		Site site = new Site();
		site.setId(1L);
		role.setSite(site);
		roleService.save(role);
	}
}
