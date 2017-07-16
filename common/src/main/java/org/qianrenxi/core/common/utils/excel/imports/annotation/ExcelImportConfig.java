package org.qianrenxi.core.common.utils.excel.imports.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述：Excel 导入注解类: 
 * 
 * 1、导入的类必须添加该注解类 
 * 2、默认从第1行导入 
 * 3、如果 notNullCols 方法中每一列都为空，则系统认为该条数据不正确，需去除 
 * @author ：gujun
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface ExcelImportConfig {
	
	/**
	 * 描述：读取Excel数据记录的开始行，默认为1，即从第1行开始 
	 * @return 有效数据记录的开始行
	 */
	
	int startLine() default 1;

	
	/**
	 * 
	 * 描述：读取Excel数据记录非空列索引数组，默认为null，即每一列都可以为空；列索引从0开始
	 * 
	 * 1、如果非空列数组为null，则读取每一行的数据作为一条记录
	 * 2、如果非空列数组不为null，例如为[0]，则若每行的第0列为空，则不读取该行记录；反之读取该行记录
	 * 3、如果非空列数组不为null，例如为[0,1]，则若每行的第0列、第1列同时都为空，则不读取该行记录；反之读取该行记录
	 * @return Excel数据记录非空列索引数组
	 */
	@Deprecated
	int[] notNullCols() default {};
}
