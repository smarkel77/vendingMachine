package com.techelevator;

public class Gum extends Snack{
	
	
	public Gum(String location, String name, String sound, double price) {
		super(location, name, sound, price);	
	}
	
	
	
	
	@Override
	public String getSound() {
		return "Chew Chew, Yum!";
	}
	

}
