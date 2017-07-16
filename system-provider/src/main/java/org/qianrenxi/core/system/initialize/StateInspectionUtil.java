package org.qianrenxi.core.system.initialize;

import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.enity.User.AccountStatus;
import org.qianrenxi.core.system.service.SiteService;
import org.qianrenxi.core.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 初始化状态检测工具
 * @author Tony
 *
 */
public class StateInspectionUtil {
	Logger logger = LoggerFactory.getLogger(StateInspectionUtil.class);
	
	private UserService userService;
	private SiteService siteService;
	
	public StateInspectionUtil(ApplicationContext context) {
		this.userService = context.getBean(UserService.class);
		this.siteService = context.getBean(SiteService.class);
	}
	
	public void run(){
		if(null!=this.userService && null!=this.siteService){
			init();
		}
	}
	
	private void init(){
		Site site = siteService.getOne(1L);
		User user = userService.getOne(1L);
		long siteCount = siteService.count();
		if(null == site && siteCount == 0){
			logger.info("系统没有初始化数据，正在执行初始化...");
			// 执行初始化
			// 创建站点
			site = createRootSite();
			// 创建用户
			user = createSupperUser(site);
			
			// 导入系统权限
			// 创建超级管理员角色
			// 授予超级管理员角色
			// 导入系统默认配置和其他初始化数据
			
			// TODO: Logger 记录过程 
			logger.info("系统初始化数据成功！");
		}
	}
	
	private Site createRootSite(){
		Site site = new Site();
		site.setId(1L);
		site.setName("Root Site");
		site.setShortName("Root Site");
		site.setDescription("This Site is inited by system default. There is one and only one root site in the system.");
		
		return this.siteService.save(site);
	}
	
	private User createSupperUser(Site site) {
		User user = new User();
		user.setId(1L);
		user.setUsername("system");
		user.setFirstName("System Admin");
		user.setDisplayName("System Admin");
		user.setEmail("system@parim.net");
		user.setPassword("888888");
		user.setSalt("afasdfsakdfhajkhf");
		user.setStatus(AccountStatus.ACTIVE);
		// user.setSite(site);
		
		return this.userService.save(user);
	}
}
