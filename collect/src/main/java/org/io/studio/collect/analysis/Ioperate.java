package org.io.studio.collect.analysis;

import org.apache.poi.ss.usermodel.Row;
import org.io.studio.collect.entity.CourseBean;

public interface Ioperate {
	public void handle(Row row,CourseBean course);
	public boolean isEndPoint(Row row);
}
