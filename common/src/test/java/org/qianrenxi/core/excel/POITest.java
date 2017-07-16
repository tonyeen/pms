package org.qianrenxi.core.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.qianrenxi.core.common.utils.FileUtil;
import org.qianrenxi.core.common.utils.excel.exports.ExcelToolsExport;
import org.qianrenxi.core.common.utils.excel.imports.ExcelToolsImport;
import org.qianrenxi.core.excel.dto.ExportDto;
import org.qianrenxi.core.excel.dto.ImportDto;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class POITest {
	
	@Test
	public void exportTest() {
		
		List<ExportDto> list = new ArrayList<ExportDto>();
		list.add(new ExportDto("jack",20));
		list.add(new ExportDto("Annie",18));
		
		try {
			byte[] createExcelExport = ExcelToolsExport.createExcelExport(list , "sheet0");
			FileUtil.writeByte("D:\\test.xlsx", createExcelExport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void importTest() {
		
		try {
			List<ImportDto> excelImport = ExcelToolsImport.excelImport(new FileInputStream(new File("D:\\test.xlsx")), 
					ImportDto.class);
			String writeValueAsString = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(excelImport);
			System.out.println(writeValueAsString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
