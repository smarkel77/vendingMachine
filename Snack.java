package com.techelevator;

import java.text.DecimalFormat;

public class Snack {

	DecimalFormat twoDForm = new DecimalFormat("#0.00");
	
	protected String location;
	protected String name;
	protected String sound;
	protected double price;
	protected int quantity = 5;
	protected int totalSold = 0;

	// constructor
	public Snack(String location, String name, String sound, double price) {
		this.location = location;
		this.name = name;
		this.sound = sound;
		this.price = price;

	}	

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLocation() {
		return location;
	}
	public String toString() {
		String remaining = "items remaining";
		String money = "$";
		return String.format("%-4s%-21s%1s%-6s%-2d%2s", this.location, this.name, money, twoDForm.format(this.price), this.quantity, remaining);
	}

	public int getQuanity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public String getSound() {
		return sound;
	}

	public double getPrice() {
		return price;
	}

}
