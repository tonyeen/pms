package org.qianrenxi.core.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface SystemResource {
	String name() default "";
}
