package org.io.studio.collect.entity;

public class StudentBean {
	private Long xh;// 学号
	private String xm;// 姓名
	private Integer pszp;// 平时成绩
	private Integer qzcj;// 期中成绩
	private Integer zhcs;// 综合测试
	private Integer zpcj;// 总评成绩
	private String szpcj;// 总评成绩 文字
	private Integer bkcj;// 补考成绩
	private Integer cxcj;// 重修成绩

	public String getSzpcj() {
		return szpcj;
	}

	public void setSzpcj(String szpcj) {
		this.szpcj = szpcj;
	}

	public Long getXh() {
		return xh;
	}

	public void setXh(Long xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public Integer getPszp() {
		return pszp;
	}

	public void setPszp(Integer pszp) {
		this.pszp = pszp;
	}

	public Integer getQzcj() {
		return qzcj;
	}

	public void setQzcj(Integer qzcj) {
		this.qzcj = qzcj;
	}

	public Integer getZhcs() {
		return zhcs;
	}

	public void setZhcs(Integer zhcs) {
		this.zhcs = zhcs;
	}

	public Integer getZpcj() {
		return zpcj;
	}

	public void setZpcj(Integer zpcj) {
		this.zpcj = zpcj;
	}

	public Integer getBkcj() {
		return bkcj;
	}

	public void setBkcj(Integer bkcj) {
		this.bkcj = bkcj;
	}

	public Integer getCxcj() {
		return cxcj;
	}

	public void setCxcj(Integer cxcj) {
		this.cxcj = cxcj;
	}

	@Override
	public String toString() {
		return "StudentBean [xh=" + xh + ", xm=" + xm + ", pszp=" + pszp
				+ ", qzcj=" + qzcj + ", zhcs=" + zhcs + ", zpcj=" + zpcj
				+ ", bkcj=" + bkcj + ", cxcj=" + cxcj + "]";
	}

}
