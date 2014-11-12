package org.io.studio.collect.generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.io.studio.collect.entity.CourseBean;
import org.io.studio.collect.entity.StudentBean;

public class GenerateData {

	static int TYPE_STRING = HSSFCell.CELL_TYPE_STRING;

	public static void process(List<CourseBean> courses) {
		HSSFWorkbook book = makeFile();

		for (CourseBean c : courses) {
			handle(book, c);
		}
		try {
			File file=new File("/data/结果/");
		 
			if(!file.exists()){
				file.mkdirs();
			}else{
				file.delete();
			}
			book.write(new FileOutputStream("/data/结果/汇总.xls"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void handle(HSSFWorkbook book, CourseBean course) {
		HSSFSheet sheet = book.getSheetAt(0);
		int rowNum = sheet.getLastRowNum()+1;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (StudentBean s : course.getStudents()) {
			int i = 0;
			row = sheet.createRow(rowNum++);
			cell = row.createCell(i++, TYPE_STRING);
			cell.setCellValue(rowNum-1);
			createCell(i++, row, course.getXb());
			createCell(i++, row, course.getBjjc());
			createCell(i++, row, course.getKcmc());
			createCell(i++, row, s.getXh().toString());
			createCell(i++, row, s.getXm());
			createCell(i++, row, s.getPszp(), "");
			createCell(i++, row, s.getQzcj(), "");
			createCell(i++, row, s.getZhcs(), "");
			createCell(i++, row, s.getZpcj(), s.getSzpcj());
			createCell(i++, row, "");
			createCell(i++, row, course.getKkdw());
			createCell(i++, row, course.getRkjs());

		}
	}

	private static HSSFCell createCell(int id, HSSFRow row, String val) {
		HSSFCell cell = row.createCell(id, TYPE_STRING);
		cell.setCellValue(val);
		return cell;
	}

	private static HSSFCell createCell(int id, HSSFRow row, Integer val,
			String str) {
		HSSFCell cell = row.createCell(id, TYPE_STRING);
		if (val == null) {
			cell.setCellValue(str);
		} else {
			cell.setCellValue(val);
		}
		return cell;
	}

	private static HSSFWorkbook makeFile() {
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("汇总");
		HSSFRow row=sheet.createRow(0);
		int i=0;
		createCell(i++, row, "");
		createCell(i++, row, "院系");
		createCell(i++, row, "班级简称");
		createCell(i++, row, "课程名称");
		createCell(i++, row, "学号");
		createCell(i++, row, "姓名");
		createCell(i++, row, "平时成绩");
		createCell(i++, row, "期中成绩");
		createCell(i++, row, "期末成绩");
		createCell(i++, row, "总评成绩");
		createCell(i++, row, "备注");
		createCell(i++, row, "开课单位");
		createCell(i++, row, "任课教师");

		return book;

	}
}
