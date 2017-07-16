package org.qianrenxi.core.common.utils.excel.exports.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 描述：Excel 导出注解类 
 * 1、导出的类必须添加该注解类 <br>
 * 2、默认导出的Excel第0行为标题行，对象记录行从第1行开始
 * @author ：gujun
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface ExcelExportConfig {
	
	/**
	 * 描述： 生成Excel文件的 标题所在的行数，默认为0
	 * @return 生成Excel文件的 标题所在的行数
	 */
	int headerRow() default 0;
	
	/**
	 * 描述：生成Excel文件的 对象记录所在的行数，默认从1开始 
	 * @return 生成Excel文件的 对象记录所在的行数
	 */
	int lineStartRow() default 1;
}
