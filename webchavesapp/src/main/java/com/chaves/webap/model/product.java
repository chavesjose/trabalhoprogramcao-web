package com.chaves.webap.model;

public class product {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	private String name;
	@Override
	public String toString() {
		return "product [id=" + id + ", name=" + name + ", privce=" + price + ", qt=" + qt + "]";
	}
	private String price;
	private String qt;
	

}
