package org.qianrenxi.core.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * Spring 上下文对象获取工具
 * 
 *
 */
@Component
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(SpringUtil.applicationContext == null){
			SpringUtil.applicationContext = applicationContext;
		}
	}

	/**
	 * 获取applicationContext  
	 * @return
	 */
    public static ApplicationContext getApplicationContext() {  
       return applicationContext;  
    }  
  
    /**
     * 通过name获取 Bean.  
     * @param name Bean的名称
     * @return Bean对象或null
     */
    public static Object getBean(String name){  
       return getApplicationContext().getBean(name);  
    }  
  
    /**
     * 通过class获取Bean.  
     * @param clazz Class对象
     * @return Bean对象或null
     */
    public static <T> T getBean(Class<T> clazz){  
       return getApplicationContext().getBean(clazz);  
  
    }  
  
    /**
     * 通过name,以及Clazz返回指定的Bean  
     * @param name Bean的名称
     * @param clazz Class对象
     * @return Bean对象或null
     */
    public static <T> T getBean(String name,Class<T> clazz){  
       return getApplicationContext().getBean(name, clazz);  
    }  
}
