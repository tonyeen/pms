package org.qianrenxi.core.system.spring.aop;

import java.lang.reflect.Field;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.security.UserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author gujun
 */
@Aspect
@Configuration
public class DefautSiteHandlerAspect {

	private static final Logger logger = LoggerFactory.getLogger(DefautSiteHandlerAspect.class);

	@Pointcut("execution(public * org.qianrenxi.core.*.repository.jpa..*.save*(..))")
	public void saveCurrentSiteId() {
	}

	@Before("saveCurrentSiteId()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>@Pointcut saveCurrentSiteId");
		Object[] args = joinPoint.getArgs();
		UserToken userToken = (UserToken) SecurityUtils.getSubject().getPrincipal();
		if (userToken == null) return;//当前用户为空时不处理
		
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			if (o instanceof Iterable) {//批量保存
				Iterable<Object> iter = (Iterable<Object>) o;
				iter.forEach((Object t) -> {
					try {
						this.reflectSiteField(userToken, t);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				});
				args[i] = iter;// 修改过的对象设置当方法参数里面
			} else {//单个保存
				reflectSiteField(userToken, o);
				args[i] = o;
			}
		}
	}

	/**
	 * 反射处理site字段
	 * @param userToken
	 * @param o
	 * @throws IllegalAccessException
	 */
	private void reflectSiteField(UserToken userToken, Object o) throws IllegalAccessException {
		Field[] declaredFields = o.getClass().getDeclaredFields();
		Field declaredField = null;
		for (int j = 0; j < declaredFields.length; j++) {
			if (declaredFields[j].getType() == Site.class) {
				declaredField = declaredFields[j];
				break;
			}
		}
		// Field declaredField = o.getClass().getDeclaredField("site");
		if (declaredField != null) {
			declaredField.setAccessible(true);
			if (declaredField.get(o) == null) {
				declaredField.set(o, userToken.getCurrentSite());
				logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>> setCurrentSite :"+ declaredField);
			}
		}
	}
}
