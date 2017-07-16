package org.qianrenxi.core.system.initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
// TODO: 加上条件注释，分环境决定是否加载和执行
public class ContextLoadedHandler implements ApplicationListener<ContextRefreshedEvent> {
	Logger logger = LoggerFactory.getLogger(ContextLoadedHandler.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		if(isRootContext(context)){
			logger.info("检测运行环境...");
			
			logger.info("检测系统安装和初始化...");
			// new StateInspectionUtil(context).run();
			
			logger.info("检测系统版本升级...");
			logger.info("加载系统配置...");
			logger.info("启动系统监控...");
		}
	}
	
	private boolean isRootContext(ApplicationContext context){
		return context != null && context.getParent() == null;
	}
	
}
