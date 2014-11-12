package org.io.studio.collect.analysis;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.io.studio.collect.entity.CourseBean;

public abstract class AOperateProcess implements Ioperate {
	public static final String TAG_END = "班级在    籍人数";

	public abstract void handle(Row row, CourseBean course);

	public boolean isEndPoint(Row row) {
		try {
			int sheetLines = row.getLastCellNum();
			for (int r = 0; r < sheetLines; r++) {
				Cell cell = row.getCell(r);
				String conten=getCellValue(cell);
				if (conten!=null&&conten.contains(TAG_END)) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getCellValue(Cell cell) {
		// TODO Auto-generated method stub
		if (cell instanceof HSSFCell) {
			HSSFCell c=(HSSFCell) cell;
			return getCellValue(c);
		}
		if (cell instanceof XSSFCell) {
			XSSFCell xc=(XSSFCell) cell;
			return getCellValue(xc);
		}
		return null;
	}

	public boolean isEndPoint(HSSFCell cell) {
		boolean is = false;
		String val = getCellValue(cell);
		if (val.contains(TAG_END)) {
			is = true;
		}
		return is;
	}

	public boolean isEndPoint(XSSFCell cell) {
		boolean is = false;
		String val = getCellValue(cell);
		if (val.contains(TAG_END)) {
			is = true;
		}
		return is;
	}

	public String getCellValue(HSSFCell cell) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				// cell.getCellFormula();
				try {
					value = String.valueOf(cell.getRichStringCellValue());
				} catch (IllegalStateException e) {
					value = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				Double d = cell.getNumericCellValue();
				DecimalFormat df = new DecimalFormat("#");
				value = df.format(d);
				// value = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = String.valueOf(cell.getRichStringCellValue());
				break;
			}
		}
		return value;
	}

	public String getCellValue(XSSFCell cell) {
		String value = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case XSSFCell.CELL_TYPE_FORMULA:
				// cell.getCellFormula();
				try {
					value = String.valueOf(cell.getNumericCellValue());
				} catch (IllegalStateException e) {
					value = String.valueOf(cell.getRichStringCellValue());
				}
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				value = String.valueOf(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = String.valueOf(cell.getRichStringCellValue());
				break;
			}
		}
		return value;
	}

	public Integer getIntegerValue(HSSFCell cell) {

		Integer i = null;
		String tmp = getCellValue(cell);
		if (null != tmp) {
			if(tmp.contains(".")){
				tmp=tmp.substring(0, tmp.indexOf("."));
			}
			try {
				i = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
}
