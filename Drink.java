package com.techelevator;

public class Drink extends Snack{
	
	public Drink(String location, String name, String sound, double price) {
		super(location, name, sound, price);	
	}
	
	
	
	@Override
	public String getSound() {
		return "Glug Glug, Yum!";
	}

}
