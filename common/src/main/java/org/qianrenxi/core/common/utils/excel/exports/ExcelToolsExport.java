package org.qianrenxi.core.common.utils.excel.exports;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.qianrenxi.core.common.utils.excel.exports.annotation.ExcelExportCol;
import org.qianrenxi.core.common.utils.excel.exports.annotation.ExcelExportConfig;

/**
 * 描述：Excel 导出工具类 
 * @author ：gujun
 */
public class ExcelToolsExport {
	private static final String DATE_FORMAT = "yyyy-mm-dd";
	private static final String NUMERIC_FORMAT = "#############0.00######";
	private static final int BUFFER_SIZE = 40960;


	public static <T> byte[] createExcelExport(List<T> list, String sheetName) throws Exception {
		checkValidate(list);

		Workbook wb = createWorkbook(true);
		sheetName = (sheetName == null) || (sheetName.equals("")) ? "sheet1" : sheetName;
		Sheet sheet = wb.createSheet(sheetName);
		setExcelHeader(sheet, list.get(0));
		setExcelLines(sheet, list, wb);

		return getByteFormWb(wb);
	}

	public static byte[] createExcelExport(Map<String, List<?>> map) throws Exception {
		if ((map != null) && (map.size() > 0)) {
			Workbook wb = createWorkbook(true);

			Iterator<String> it = map.keySet().iterator();
			Sheet sheet = null;
			String sheetName = null;
			List<?> list = null;
			while (it.hasNext()) {
				sheetName = (String) it.next();
				list = (List) map.get(sheetName);

				checkValidate(list);

				sheet = wb.createSheet(sheetName);
				setExcelHeader(sheet, list.get(0));
				setExcelLines(sheet, list, wb);
			}
			return getByteFormWb(wb);
		}
		return null;
	}

	private static <T> void setExcelLines(Sheet sheet, List<T> list, Workbook wb) throws Exception {
		int lineStartRow = getLineStartRow(list.get(0).getClass());
		Row row = null;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(lineStartRow);
			obj2Cell(row, list.get(i), wb);
			lineStartRow++;
		}
	}

	private static <T> void setExcelHeader(Sheet sheet, T t) throws Exception {
		int headRow = getHeaderRow(t.getClass());
		Row row = sheet.createRow(headRow);

		List<Field> list = getExcelExportColAnnoFields(t.getClass());
		ExcelExportCol excelExportCol = null;
		int cols = 0;
		String colHeaderDesc = null;
		for (Field f : list) {
			excelExportCol = (ExcelExportCol) f.getAnnotation(ExcelExportCol.class);
			cols = excelExportCol.colIndex();
			colHeaderDesc = excelExportCol.colHeaderDesc();
			row.createCell(cols).setCellValue(colHeaderDesc);
		}
	}

	private static <T> void obj2Cell(Row row, T t, Workbook wb) throws Exception {
		List<Field> list = getExcelExportColAnnoFields(t.getClass());

		ExcelExportCol excelExportCol = null;
		Cell cell = null;
		int cols = 0;
		Object value = null;

		PropertyDescriptor pd = null;
		Method m = null;
		for (Field f : list) {
			excelExportCol = (ExcelExportCol) f.getAnnotation(ExcelExportCol.class);
			cols = excelExportCol.colIndex();

			pd = new PropertyDescriptor(f.getName(), t.getClass());
			m = pd.getReadMethod();
			value = m.invoke(t, new Object[0]);
			if (value == null) {
				return;
			}
			cell = row.createCell(cols);
			fillCell(value, cell, wb);
		}
	}

	private static void fillCell(Object value, Cell cell, Workbook wb) {
		if ((value instanceof java.util.Date)) {
			java.util.Date d = (java.util.Date) value;
			DataFormat format = wb.createDataFormat();

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));

			cell.setCellStyle(cellStyle);
			cell.setCellValue(d);
			return;
		}
		if ((value instanceof java.sql.Date)) {
			java.sql.Date d = (java.sql.Date) value;
			cell.setCellValue(d);
			return;
		}
		if ((value instanceof Timestamp)) {
			Timestamp ts = (Timestamp) value;
			cell.setCellValue(ts);
			return;
		}
		if ((value instanceof BigDecimal)) {
			BigDecimal b = (BigDecimal) value;
			cell.setCellValue(b.doubleValue());
			return;
		}
		if ((value instanceof Double)) {
			Double d = (Double) value;
			DataFormat format = wb.createDataFormat();

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(format.getFormat("#############0.00######"));

			cell.setCellStyle(cellStyle);
			cell.setCellValue(d.doubleValue());
			return;
		}
		if ((value instanceof Float)) {
			Float f = (Float) value;
			DataFormat format = wb.createDataFormat();

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(format.getFormat("#############0.00######"));

			cell.setCellStyle(cellStyle);
			cell.setCellValue(f.floatValue());
			return;
		}
		if ((value instanceof Long)) {
			Long l = (Long) value;
			cell.setCellValue(l.longValue());
			return;
		}
		if ((value instanceof Integer)) {
			Integer i = (Integer) value;
			cell.setCellValue(i.intValue());
			return;
		}
		if ((value instanceof Boolean)) {
			Boolean b = (Boolean) value;
			cell.setCellValue(b.booleanValue());
			return;
		}
		if ((value instanceof String)) {
			String s = (String) value;
			cell.setCellValue(s);
			return;
		}
	}

	private static Workbook createWorkbook(boolean flag) {
		Workbook wb;
		if (flag) {
			wb = new XSSFWorkbook();
		} else {
			wb = new HSSFWorkbook();
		}
		return wb;
	}

	private static byte[] getByteFormWb(Workbook wb) throws Exception {
		if (wb != null) {
			ByteArrayOutputStream byStream = new ByteArrayOutputStream(40960);
			wb.write(byStream);
			return byStream.toByteArray();
		}
		return null;
	}

	private static <T> int getHeaderRow(Class<T> cla) throws Exception {
		return ((ExcelExportConfig) cla.getAnnotation(ExcelExportConfig.class)).headerRow();
	}

	private static <T> int getLineStartRow(Class<T> cla) throws Exception {
		return ((ExcelExportConfig) cla.getAnnotation(ExcelExportConfig.class)).lineStartRow();
	}

	private static <T> List<Field> getExcelExportColAnnoFields(Class<T> cla) throws Exception {
		List<Field> list = new ArrayList();
		Field[] fields = cla.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(ExcelExportCol.class)) {
				list.add(f);
			}
		}
		return list;
	}

	private static boolean checkValidate(List<?> list) throws Exception {
		if ((list == null) || (list.size() == 0)) {
			throw new Exception("指定的对象集合数据源为null，或者长度等于0！");
		}
		Class<?> cla = list.get(0).getClass();
		if (!cla.isAnnotationPresent(ExcelExportConfig.class)) {
			throw new Exception("指定的实体类" + list.get(0).getClass().getName() + " 缺少ExcelExportConfig注解！");
		}
		int headerRow = getHeaderRow(cla);
		int lineStartRow = getLineStartRow(cla);
		if (headerRow >= lineStartRow) {
			throw new Exception("指定的实体类" + cla.getName() + " 设置的标题头行应该小于内容记录开始行！");
		}
		if (getExcelExportColAnnoFields(cla).size() == 0) {
			throw new Exception("指定的实体类" + cla.getName() + " 属性缺少ExcelExportCol注解！");
		}
		return true;
	}
}
