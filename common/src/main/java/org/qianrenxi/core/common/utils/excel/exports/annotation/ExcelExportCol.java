package org.qianrenxi.core.common.utils.excel.exports.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 描述：Excel 导出属性注解类
 * 
 * 1、导出的类必须添加注解类ExcelExportConfig
 * 2、该注解类用在类属性上，获取Excel的列标题头和列索引（从0开始）
 * @author ：gujun
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface ExcelExportCol {
	
	/**
	 * 描述：列标题头说明 
	 * @return 列标题头说明
	 */
	
	String colHeaderDesc();
	
	
	/**
	 * 描述：所在列索引，下标从0开始 
	 * @return 所在列索引，下标从0开始
	 */
	int colIndex();
}
