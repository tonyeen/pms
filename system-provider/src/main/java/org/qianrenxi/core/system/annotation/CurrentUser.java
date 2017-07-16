package org.qianrenxi.core.system.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.qianrenxi.core.system.constant.Constants;


/**
 * <p>绑定当前登录的用户</p>
 * <p>不同于@ModelAttribute</p>
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
	
	/**
     * 当前用户在session中的名字 默认{@link Constants#SESSION_KEY}
     *
     * @return
     */
    String value() default Constants.SESSION_KEY;
}
