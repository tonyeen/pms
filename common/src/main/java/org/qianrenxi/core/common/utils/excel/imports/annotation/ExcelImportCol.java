package org.qianrenxi.core.common.utils.excel.imports.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 描述：Excel 导入属性注解类 
 * 1、导入的类必须添加注解类ExcelImportConfig
 * 2、该注解类用在类属性上，获取Excel所在列的记录
 * @author gujun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface ExcelImportCol {
	
	/**
	 * 描述：Excel 所在列 
	 * @return Excel 所在列
	 */
	int colIndex();
	
	
	boolean nullable() default false;
}
