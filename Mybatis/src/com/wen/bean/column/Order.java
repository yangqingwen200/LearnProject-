package com.wen.bean.column;

public class Order {
	private int id;
	private String no;
	private Float price;

	public Order() {
		super();
	}

	public Order(int id, String no, Float price) {
		super();
		this.id = id;
		this.no = no;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", no=" + no + ", price=" + price + "]";
	}

}
