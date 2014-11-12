package org.io.studio.collect.generate;

import java.util.ArrayList;
import java.util.List;

import org.io.studio.collect.entity.CourseBean;

public class CollectData {
	private volatile static CollectData syncSingle;

	private CollectData() {
	}

	public static CollectData getSyncSingle() {
		if (null == syncSingle) {
			synchronized (CollectData.class) {
				if (null == syncSingle) {
					syncSingle = new CollectData();
				}
				return syncSingle;
			}
		}
		return syncSingle;
	}

	private List<CourseBean> courses = new ArrayList<CourseBean>();

	public List<CourseBean> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseBean> courses) {
		this.courses = courses;
	}

	public synchronized void add(CourseBean course) {
		courses.add(course);
	}

}
