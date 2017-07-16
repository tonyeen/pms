package org.qianrenxi.core.common.utils.excel.imports;


import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.qianrenxi.core.common.utils.excel.imports.annotation.ExcelImportCol;
import org.qianrenxi.core.common.utils.excel.imports.annotation.ExcelImportConfig;



/**
 * 描述： Excel 导入工具类<br>
 * <br>
 * 方法一：excelImport(InputStream, Class) : 将 文件流 转化为 List对象集合,sheet索引默认为0；<br>
 * 方法一：excelImport(InputStream, Class, int) : 将 文件流 转化为 List对象集合,可设置sheet索引位置<br>
 * @author ：gujun<br>
 */

public class ExcelToolsImport {
	public static <T> List<T> excelImport(InputStream fileInputStream, Class<T> cla) throws Exception {
		return excelImport(fileInputStream, cla, 0);
	}

	public static <T> List<T> excelImport(InputStream fileInputStream, Class<T> cla, int sheetIndex) throws Exception {
		checkValidate(fileInputStream, cla);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheetAt(sheetIndex);

		int rows = sheet.getLastRowNum();
		int startLine = getStartLine(cla);

		List<T> list = new ArrayList();
		Row row = null;
		T t = null;
		for (int i = startLine; i <= rows; i++) {
			row = sheet.getRow(i);
			t = addLine2List(row, cla);
			if (validateNotNullCol(t)) {
				list.add(t);
			}
		}
		return list;
	}

	private static <T> T addLine2List(Row row, Class<T> cla) throws Exception {
		T t = cla.newInstance();
		List<Field> list = getExcelImportColAnnoFields(cla);
		for (Field field : list) {
			setCell2Obj(field, row, t);
		}
		return t;
	}

	private static <T> T setCell2Obj(Field field, Row row, T t) throws Exception {
		int col = ((ExcelImportCol) field.getAnnotation(ExcelImportCol.class)).colIndex();
		Cell cell = row.getCell(col);
		if (cell != null) {
			String typeName = field.getType().getSimpleName();

			String propertyName = field.getName();
			PropertyDescriptor pd = new PropertyDescriptor(propertyName, t.getClass());
			Method m = pd.getWriteMethod();
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				String value = cell.getRichStringCellValue().getString();
				m.invoke(t, new Object[] { value });
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					m.invoke(t, new Object[] { date });
				} else {
					double d = cell.getNumericCellValue();
					if (BigDecimal.class.getSimpleName().equals(typeName)) {
						BigDecimal bigDecimal = new BigDecimal(d);
						m.invoke(t, new Object[] { bigDecimal });
					}
					if ((Double.class.getSimpleName().equals(typeName)) || ("double".equals(typeName))) {
						Double d1 = new Double(d);
						m.invoke(t, new Object[] { d1 });
					}
					if ((Float.class.getSimpleName().equals(typeName)) || ("float".equals(typeName))) {
						Float f = new Float(d);
						m.invoke(t, new Object[] { f });
					}
					if ((Integer.class.getSimpleName().equals(typeName)) || ("int".equals(typeName))) {
						Integer i = Integer.valueOf(new BigDecimal(d).intValue());
						m.invoke(t, new Object[] { i });
					}
					if ((Long.class.getSimpleName().equals(typeName)) || ("long".equals(typeName))) {
						Long l = Long.valueOf(new BigDecimal(d).longValue());
						m.invoke(t, new Object[] { l });
					}
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				boolean b = cell.getBooleanCellValue();
				m.invoke(t, new Object[] { Boolean.valueOf(b) });
				break;
			}
		}
		return t;
	}

	private static <T> int getStartLine(Class<T> cla) {
		return ((ExcelImportConfig) cla.getAnnotation(ExcelImportConfig.class)).startLine();
	}

	private static <T> List<Field> getExcelImportColAnnoFields(Class<T> cla) throws Exception {
		List<Field> fieldList = new ArrayList();
		Field[] fields = cla.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(ExcelImportCol.class)) {
				fieldList.add(f);
			}
		}
		return fieldList;
	}

	private static <T> boolean checkValidate(InputStream fileInputStream, Class<T> cla) throws Exception {
		if (fileInputStream == null) {
			throw new Exception("导入Excel生成的文件流为空！");
		}
		if (!cla.isAnnotationPresent(ExcelImportConfig.class)) {
			throw new Exception("指定的实体类" + cla.getName() + " 缺少ExcelImportConfig注解！");
		}
		if (getExcelImportColAnnoFields(cla).size() == 0) {
			throw new Exception("指定的实体类" + cla.getName() + " 属性缺少ExcelImportCol注解！");
		}
		return true;
	}
	
	
	
	private static <T> boolean validateNotNullCol(T t) throws Exception {
		boolean validate = true;
		List<Field> list = getExcelImportColAnnoFields(t.getClass());
		PropertyDescriptor pd = null;
		Method m = null;
		Object fieldValue = null;
		boolean nullable;
		for (Field f : list) {
			nullable = ((ExcelImportCol) f.getAnnotation(ExcelImportCol.class)).nullable();
			if (!nullable) {
				pd = new PropertyDescriptor(f.getName(), t.getClass());
				m = pd.getReadMethod();
				fieldValue = m.invoke(t, new Object[0]);
				if (fieldValue == null) {
					validate = false;
					break;
				}
			}
		}
		return validate;
	}

	private static <T> boolean validateNotNull(T t) throws Exception {
		boolean validate = false;
		int[] notNullCols = ((ExcelImportConfig) t.getClass().getAnnotation(ExcelImportConfig.class)).notNullCols();
		if ((notNullCols == null) || (notNullCols.length == 0)) {
			validate = true;
		} else {
			boolean[] b = new boolean[notNullCols.length];
			List<Field> list = getExcelImportColAnnoFields(t.getClass());
			PropertyDescriptor pd = null;
			Method m = null;
			Object fieldValue = null;
			int col = 0;
			for (int i = 0; i < notNullCols.length; i++) {
				for (Field f : list) {
					col = ((ExcelImportCol) f.getAnnotation(ExcelImportCol.class)).colIndex();
					if (notNullCols[i] == col) {
						pd = new PropertyDescriptor(f.getName(), t.getClass());
						m = pd.getReadMethod();
						fieldValue = m.invoke(t, new Object[0]);
						if (fieldValue == null) {
							b[i] = false;
							break;
						}
						b[i] = true;

						break;
					}
				}
			}
			for (int i = 0; i < b.length; i++) {
				validate = (validate) || (b[i]);
			}
		}
		return validate;
	}
}
