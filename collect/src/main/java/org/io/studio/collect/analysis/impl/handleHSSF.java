package org.io.studio.collect.analysis.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import org.io.studio.collect.analysis.AOperateProcess;
import org.io.studio.collect.entity.CourseBean;
import org.io.studio.collect.entity.StudentBean;

public class handleHSSF extends AOperateProcess {
	static Logger log = Logger.getLogger(handleHSSF.class);

	@Override
	public void handle(Row row, CourseBean course) {
		// TODO Auto-generated method stub
		HSSFRow hrow = (HSSFRow) row;
		log.debug(" row num " + row.getRowNum());
		int rowNum = row.getRowNum();

		switch (rowNum) {
		case 0:
			break;
		case 1:
			rowOne(hrow, course);
			break;
		case 2:
			rowTwo(hrow, course);
			break;
		case 3:
			rowThree(hrow, course);
			break;
		case 4:
			rowFour(hrow, course);
			break;
		case 5:
			break;
		default:
			defaultStudent(hrow, course);
			break;
		}

	}

	private void defaultStudent(HSSFRow row, CourseBean course) {

		HSSFCell cell = null;
		cell = row.getCell(0);
		Integer i = null;
		Long L = null;
		List<StudentBean> list = course.getStudents();
		StudentBean student = new StudentBean();
		String first = getCellValue(cell);
		if (first != null && first.length() > 0) {
			L = Long.parseLong(getCellValue(cell));
			student.setXh(L);
			cell = row.getCell(1);
			student.setXm(getCellValue(cell));
			cell = row.getCell(2);
			i = getIntegerValue(cell);
			student.setPszp(i);
			cell = row.getCell(3);
			i = getIntegerValue(cell);
			student.setQzcj(i);
			cell = row.getCell(4);
			i = getIntegerValue(cell);
			student.setZhcs(i);
			cell = row.getCell(5);
			try {
				i = getIntegerValue(cell);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.debug(e);
				i = null;
			}
			if (i == null) {
				student.setSzpcj(cell.getRichStringCellValue().toString());
			}
			student.setZpcj(i);
			cell = row.getCell(6);
			i = getIntegerValue(cell);
			student.setBkcj(i);
			cell = row.getCell(7);
			i = getIntegerValue(cell);
			student.setCxcj(i);
			list.add(student);
			student = new StudentBean();
			cell = row.getCell(9);
			String tmp = getCellValue(cell);
			if (tmp != null && tmp.length() > 0) {
				L = Long.parseLong(tmp);
				student.setXh(L);
				cell = row.getCell(10);
				student.setXm(getCellValue(cell));
				cell = row.getCell(11);
				i = getIntegerValue(cell);
				student.setPszp(i);
				cell = row.getCell(12);
				i = getIntegerValue(cell);
				student.setQzcj(i);
				cell = row.getCell(13);
				i = getIntegerValue(cell);
				student.setZhcs(i);
				cell = row.getCell(14);
				try {
					i = getIntegerValue(cell);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					i = null;
				}
				if (i == null) {
					student.setSzpcj(cell.getRichStringCellValue().toString());
				}
				student.setZpcj(i);
				cell = row.getCell(15);
				i = getIntegerValue(cell);
				student.setBkcj(i);
				cell = row.getCell(16);
				i = getIntegerValue(cell);
				student.setCxcj(i);
				list.add(student);
			}

		}

		log.debug(" student size " + course.getStudents().size());
	}

	private void rowFour(HSSFRow row, CourseBean course) {
		HSSFCell cell = null;
		cell = row.getCell(1);
		course.setKkdw(getCellValue(cell));
		cell = row.getCell(5);
		course.setRkjs(getCellValue(cell));
		String zhcsrq = getCellValue(row.getCell(11)) + ","
				+ getCellValue(row.getCell(13)) + ","
				+ getCellValue(row.getCell(15));
		course.setZhcsrq(zhcsrq);
		log.debug("第四行 数据");
		log.debug("开课单位 ：" + course.getKkdw());
		log.debug("任课教师 ：" + course.getRkjs());
		log.debug("综合测试时间 ：" + course.getZhcsrq());
	}

	private void rowThree(HSSFRow row, CourseBean course) {
		HSSFCell cell = null;
		cell = row.getCell(1);
		course.setKcmc(getCellValue(cell));
		cell = row.getCell(10);
		course.setKhfs(getCellValue(cell));
		cell = row.getCell(13);
		course.setKcxz(getCellValue(cell));
		cell = row.getCell(15);
		course.setXs(getIntegerValue(cell));
		log.debug("第三行 数据");
		log.debug("课程名称 ：" + course.getKcmc());
		log.debug("考核方式 ：" + course.getKhfs());
		log.debug("课程性质 ：" + course.getKcxz());
		log.debug("学时 ：" + course.getXs());

	}

	private void rowTwo(HSSFRow row, CourseBean course) {
		// int sheetLines = row.getLastCellNum();
		HSSFCell cell = null;
		cell = row.getCell(1);
		course.setXb(getCellValue(cell));
		cell = row.getCell(5);
		course.setZynj(getCellValue(cell));
		cell = row.getCell(10);
		course.setBjjc(getCellValue(cell));
		cell = row.getCell(15);
		course.setBjdm(getCellValue(cell));
		log.debug("第二行 数据");
		log.debug("系别 ：" + course.getXb());
		log.debug("专业年级 ：" + course.getZynj());
		log.debug("班级简称 ：" + course.getBjjc());
		log.debug("班级代码 ：" + course.getBjdm());

	}

	private void rowOne(HSSFRow row, CourseBean course) {
		HSSFCell cell = null;
		cell = row.getCell(10);
		course.setXq(getCellValue(cell));
		String nd = getCellValue(row.getCell(2)) + getCellValue(row.getCell(3))
				+ getCellValue(row.getCell(4));
		course.setNd(nd);
		log.debug("第一行 数据");
		log.debug("学期 ：" + course.getXq());
		log.debug("年度 ：" + course.getNd());
	}

}
