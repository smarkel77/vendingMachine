package com.techelevator;

public class Candy extends Snack{
	
	public Candy(String location, String name, String sound, double price) {
		super(location, name, sound, price);	
	}
	
	
	
	
	@Override
	public String getSound() {
		return "Munch Munch, Yum!";
	}

}
