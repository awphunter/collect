package org.io.studio.collect.entity;

import java.io.Serializable;

public class CollectBean implements Serializable, Comparable<CollectBean> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -57100548979089747L;
	private String yxb;
	private String bjjcc;
	private String kcmcd;
	private Long xhe;
	private String xmf;
	private Integer psjcg;
	private Integer qzcjh;
	private Integer qmcji;
	private String zpcjj;
	private String bzk;
	private String kkdwl;
	private String rkjsm;
	private String _n;
	private String _o;
	private String _p;
	private Integer xsr;
	private Integer xfs;
	private Integer xqt;
	private String _u;
	private String _v;
	private String _w;
	private String _x;

	public String getYxb() {
		return yxb;
	}

	public void setYxb(String yxb) {
		this.yxb = yxb;
	}

	public String getBjjcc() {
		return bjjcc;
	}

	public void setBjjcc(String bjjcc) {
		this.bjjcc = bjjcc;
	}

	public String getKcmcd() {
		return kcmcd;
	}

	public void setKcmcd(String kcmcd) {
		this.kcmcd = kcmcd;
	}

	public Long getXhe() {
		return xhe;
	}

	public void setXhe(Long xhe) {
		this.xhe = xhe;
	}

	public String getXmf() {
		return xmf;
	}

	public void setXmf(String xmf) {
		this.xmf = xmf;
	}

	public Integer getPsjcg() {
		return psjcg;
	}

	public void setPsjcg(Integer psjcg) {
		this.psjcg = psjcg;
	}

	public Integer getQzcjh() {
		return qzcjh;
	}

	public void setQzcjh(Integer qzcjh) {
		this.qzcjh = qzcjh;
	}

	public Integer getQmcji() {
		return qmcji;
	}

	public void setQmcji(Integer qmcji) {
		this.qmcji = qmcji;
	}

	public String getZpcjj() {
		return zpcjj;
	}

	public void setZpcjj(String zpcjj) {
		this.zpcjj = zpcjj;
	}

	public String getBzk() {
		return bzk;
	}

	public void setBzk(String bzk) {
		this.bzk = bzk;
	}

	public String getKkdwl() {
		return kkdwl;
	}

	public void setKkdwl(String kkdwl) {
		this.kkdwl = kkdwl;
	}

	public String getRkjsm() {
		return rkjsm;
	}

	public void setRkjsm(String rkjsm) {
		this.rkjsm = rkjsm;
	}

	public String get_n() {
		return _n;
	}

	public void set_n(String _n) {
		this._n = _n;
	}

	public String get_o() {
		return _o;
	}

	public void set_o(String _o) {
		this._o = _o;
	}

	public String get_p() {
		return _p;
	}

	public void set_p(String _p) {
		this._p = _p;
	}

	public Integer getXsr() {
		return xsr;
	}

	public void setXsr(Integer xsr) {
		this.xsr = xsr;
	}

	public Integer getXfs() {
		return xfs;
	}

	public void setXfs(Integer xfs) {
		this.xfs = xfs;
	}

	public Integer getXqt() {
		return xqt;
	}

	public void setXqt(Integer xqt) {
		this.xqt = xqt;
	}

	public String get_u() {
		return _u;
	}

	public void set_u(String _u) {
		this._u = _u;
	}

	public String get_v() {
		return _v;
	}

	public void set_v(String _v) {
		this._v = _v;
	}

	public String get_w() {
		return _w;
	}

	public void set_w(String _w) {
		this._w = _w;
	}

	public String get_x() {
		return _x;
	}

	public void set_x(String _x) {
		this._x = _x;
	}

	@Override
	public int compareTo(CollectBean o) {
		// TODO Auto-generated method stub
		Long result=this.xhe-o.xhe;
		if(result>0){
			return 1;
		}else if(result<0){
			return -1;
		}
		return 0;
	}

}
