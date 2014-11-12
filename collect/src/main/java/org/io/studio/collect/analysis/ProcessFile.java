package org.io.studio.collect.analysis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.io.studio.collect.analysis.impl.handleHSSF;
import org.io.studio.collect.analysis.impl.handleXSSF;
import org.io.studio.collect.entity.CourseBean;
import org.io.studio.collect.entity.StudentBean;
import org.io.studio.collect.generate.CollectData;
import org.io.studio.collect.tools.FileUtil;

/**
 * 处理文档
 * 
 * @author mr.io
 * 
 */
public class ProcessFile implements Runnable {

	static Logger log = Logger.getLogger(ProcessFile.class);
	private String filePath;
	private Workbook wb = null;
	private File file = null;
	static String FILE_TYPE_XLSX = "xlsx";
	static String FILE_TYPE_XLS = "xls";

	public ProcessFile(String allPath) {
		this.filePath = allPath;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		file = new File(filePath);
		if (file.exists() && file.isFile() && file.canRead()) {
			try {
				handle();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (RuntimeException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	private void handle() throws FileNotFoundException, IOException {
		String ext = FileUtil.getExtensionName(file.getName());
		Ioperate operate = null;
		if (FILE_TYPE_XLSX.equalsIgnoreCase(ext)) {// 2007 2010
			wb = new XSSFWorkbook(new FileInputStream(file));
			log.debug("file is 2007+");
			operate = new handleXSSF();
		} else if (FILE_TYPE_XLS.equalsIgnoreCase(ext)) {// 2003 HSSFWorkbook
			wb = new HSSFWorkbook(new FileInputStream(file));
			log.debug("file is 2003");
			operate = new handleHSSF();
		} else {
			log.info("文件不需要处理");
			return;
		}
		int iSheetNum = wb.getNumberOfSheets() >= 1 ? 1 : 1;

		log.debug("file has sheets " + iSheetNum);
		CourseBean course = new CourseBean();
		for (int i = 0; i < iSheetNum; i++) {
			Sheet sheet = wb.getSheetAt(i);
			int totalRows = sheet.getLastRowNum();
			log.debug(i + " sheet has rows : " + totalRows);
			if (totalRows > 0) {
				for (int r = 0; r < totalRows; r++) {
					Row row = sheet.getRow(r);
					if (operate.isEndPoint(row)) {
						break;
					}
					operate.handle(row, course);
				}
			}
		}
		CollectData cd = CollectData.getSyncSingle();
		cd.add(course);
		log.info("over");
		
		printfData();
	}

	private void printfData() {
		// TODO Auto-generated method stub
		CollectData cd = CollectData.getSyncSingle();
		List<CourseBean> list=cd.getCourses();
		for(CourseBean c:list){
			System.out.println(c.toString());
			List<StudentBean> tmp=c.getStudents();
			for(StudentBean s:tmp){
				System.out.println(s.toString());
			}
		}
	}
}
