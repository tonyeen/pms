package org.qianrenxi.core.excel.dto;

import org.qianrenxi.core.common.utils.excel.exports.annotation.ExcelExportCol;
import org.qianrenxi.core.common.utils.excel.exports.annotation.ExcelExportConfig;

@ExcelExportConfig
public class ExportDto {
	
	public ExportDto(String name ,int age){
		this.name = name;
		this.age = age;
	}
	
	@ExcelExportCol(colHeaderDesc="年齡",colIndex=1)
	private Integer age;
	
	@ExcelExportCol(colHeaderDesc="名稱",colIndex=0)
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}
