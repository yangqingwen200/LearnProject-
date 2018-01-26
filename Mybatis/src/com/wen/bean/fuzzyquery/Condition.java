package com.wen.bean.fuzzyquery;

/**
 * 查询的条件类,所有的查询条件的值都被封装成一个类
 * 
 * @author wenwen
 * 
 *         2015年6月27日 下午1:41:34
 */
public class Condition {

	private String queryname;
	private int minAge;
	private int maxAge;

	public Condition(String queryname, int minAge, int maxAge) {
		super();
		this.queryname = queryname;
		this.minAge = minAge;
		this.maxAge = maxAge;
	}

	public Condition() {
		super();
	}

	public String getQueryname() {
		return queryname;
	}

	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

}
