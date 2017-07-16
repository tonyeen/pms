package org.qianrenxi.core.system.initialize;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
/**
 * Interface used to indicate that a bean should run when it is contained within a SpringApplication. Multiple ApplicationRunner beans can be defined within the same application context and can be ordered using the Ordered interface or @Order annotation.
 * 
 * 参见上述 ApplicationRunner 的注释，bean 被加载即可能被执行，
 * 不是 ApplicationContext 加载完了才执行，感觉不可控
 * 
 * @author tony
 *
 */
public class InitialRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// System.out.println(args.getSourceArgs());
		
	}

}
