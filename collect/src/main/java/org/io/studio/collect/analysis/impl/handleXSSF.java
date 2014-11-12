package org.io.studio.collect.analysis.impl;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.io.studio.collect.analysis.AOperateProcess;
import org.io.studio.collect.entity.CourseBean;

public class handleXSSF extends AOperateProcess {
	static Logger log = Logger.getLogger(handleXSSF.class);

	@Override
	public void handle(Row row, CourseBean course) {
		XSSFRow hrow = (XSSFRow) row;
		int sheetLines = hrow.getLastCellNum();
		for (int r = 0; r < sheetLines; r++) {
			log.debug("读取第" + r + "列数据");
			XSSFCell cell = hrow.getCell(r);
			log.debug("cell RawValue:" + cell.getRawValue());
			log.debug("cell CellType:" + cell.getCellType());
			if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
				log.debug("cell :" + cell.getStringCellValue());
			}

		}

	}

}
