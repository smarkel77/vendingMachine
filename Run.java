package com.techelevator;

import java.util.Scanner;

public class Run {

	private Menu menu;
	private boolean onOff = true;
	Scanner in = new Scanner(System.in);
	VendingMachine vending;

	public Run(Menu menu, VendingMachine vend) {
		this.menu = menu;
		this.vending = vend;
	}

	public void run() {
		while (onOff) {
			String choice = menu.mainMenu();
			if (choice.equals("1")) {
				for (String i : vending.getMap().keySet()) {
					Snack sk = vending.getMap().get(i);
					System.out.println(sk.toString());
				}
				System.out.println("\n");
			} else if (choice.equals("2")) {
				menu.purchaseMenu();
			} else if (!(choice.equals("1") && choice.equals("2"))) {
				System.out.println("Invalid. Please choose 1 or 2.");
			}
		}
	}

	public static void main(String[] args) {
		VendingMachine vend = new VendingMachine();
		Menu menu = new Menu(System.in, System.out, vend);
		Run cli = new Run(menu, vend);
		cli.run();
	}
}