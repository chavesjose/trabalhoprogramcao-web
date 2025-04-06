package com.chaves.webap.model;

public class Produto {
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQt() {
		return qt;
	}
	public Produto(int id, String name, double price, double qt) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.qt = qt;
	}
	public void setQt(double qt) {
		this.qt = qt;
	}
	private String name;
	private double price;
	private double qt;

}
