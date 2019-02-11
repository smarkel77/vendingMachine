package com.techelevator;

public class Chips extends Snack {

	public Chips(String location, String name, String sound, double price) {
		super(location, name, "Crunch Crunch, Yum!", price);	
	}
	
	

	@Override
	public String getSound() {
		return  "Crunch Crunch, Yum!";
	}
	

}
