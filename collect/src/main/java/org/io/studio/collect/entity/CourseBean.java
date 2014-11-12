package org.io.studio.collect.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程
 * 
 * @author mr.io
 * 
 */
public class CourseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4377708689899802765L;
	private String xq;// 学期
	private String nd;// 年度
	private String xb; // 系别
	private String zynj;// 专业年级
	private String bjjc;// 班级简称
	private String bjdm;// 班级代码
	private String kcmc;// 课程名称
	private String khfs;// 考核方式
	private String kcxz;// 课程性质
	private Integer xs;// 学时
	private String kkdw;// 开课单位
	private String rkjs;// 任课教师
	private String zhcsrq;// 综合测试日期
	private List<StudentBean> students;// 学生

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZynj() {
		return zynj;
	}

	public void setZynj(String zynj) {
		this.zynj = zynj;
	}

	public String getBjjc() {
		return bjjc;
	}

	public void setBjjc(String bjjc) {
		this.bjjc = bjjc;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public String getKhfs() {
		return khfs;
	}

	public void setKhfs(String khfs) {
		this.khfs = khfs;
	}

	public String getKcxz() {
		return kcxz;
	}

	public void setKcxz(String kcxz) {
		this.kcxz = kcxz;
	}

	public Integer getXs() {
		return xs;
	}

	public void setXs(Integer xs) {
		this.xs = xs;
	}

	public String getKkdw() {
		return kkdw;
	}

	public void setKkdw(String kkdw) {
		this.kkdw = kkdw;
	}

	public String getRkjs() {
		return rkjs;
	}

	public void setRkjs(String rkjs) {
		this.rkjs = rkjs;
	}

	public String getZhcsrq() {
		return zhcsrq;
	}

	public void setZhcsrq(String zhcsrq) {
		this.zhcsrq = zhcsrq;
	}

	public List<StudentBean> getStudents() {

		if (null == students) {
			students = new ArrayList<StudentBean>();
		}
		return students;
	}

	public void setStudents(List<StudentBean> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "CourseBean [xq=" + xq + ", nd=" + nd + ", xb=" + xb + ", zynj="
				+ zynj + ", bjjc=" + bjjc + ", bjdm=" + bjdm + ", kcmc=" + kcmc
				+ ", khfs=" + khfs + ", kcxz=" + kcxz + ", xs=" + xs
				+ ", kkdw=" + kkdw + ", rkjs=" + rkjs + ", zhcsrq=" + zhcsrq
				+ ", students=" + students + "]";
	}

	public String toSampleString() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("xq " + xq + ",");
		sb.append("nd " + nd + ",");
		sb.append("xb " + xb + ",");
		sb.append("zynj " + zynj + ",");
		sb.append("bjjc " + bjjc + ",");
		sb.append("bjdm " + bjdm + ",");
		sb.append("kcmc " + kcmc + ",");
		sb.append("khfs " + khfs + ",");
		sb.append("kcxz " + kcxz + ",");
		sb.append("xs " + xs + ",");
		sb.append("kkdw " + kkdw + ",");
		sb.append("rkjs " + rkjs + ",");
		sb.append("zhcsrq " + zhcsrq + " ");
		return sb.toString();
	}

}
