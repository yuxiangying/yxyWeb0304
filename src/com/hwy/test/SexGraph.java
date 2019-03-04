package com.hwy.test;

import java.math.BigDecimal;

public class SexGraph {
	private String sex;
	private int num;
	private BigDecimal proportion;
	
	public SexGraph() {
		super();
	}
	public SexGraph(String sex, int num, BigDecimal proportion) {
		super();
		this.sex = sex;
		this.num = num;
		this.proportion = proportion;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public BigDecimal getProportion() {
		return proportion;
	}
	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
	
}
