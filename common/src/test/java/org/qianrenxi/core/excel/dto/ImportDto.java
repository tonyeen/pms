package org.qianrenxi.core.excel.dto;

import org.qianrenxi.core.common.utils.excel.imports.annotation.ExcelImportCol;
import org.qianrenxi.core.common.utils.excel.imports.annotation.ExcelImportConfig;

@ExcelImportConfig(startLine=1)
public class ImportDto {
	
	public ImportDto(){
	}
	
	public ImportDto(String name ,int age){
		this.name = name;
		this.age = age;
	}
	
	@ExcelImportCol(colIndex=0)
	private String name;
	
	@ExcelImportCol(colIndex=1,nullable=true)
	private Integer age;

	
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
