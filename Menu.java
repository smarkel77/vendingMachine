package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private VendingMachine vend;

	public Menu(InputStream input, OutputStream output, VendingMachine vend) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		this.vend = vend;
	}

	public String mainMenu() {
		System.out.println("1) Display Vending Machine Items");
		System.out.println("2) Purchase");
		String choice = in.nextLine();
		try {
			if (choice.equals("1") || choice.equals("2"))
				return choice;
		} catch (Exception e) {
			System.out.println("Please choose 1 or 2");

		}
		return choice;
	}

	public String purchaseMenu() {
		boolean purchaseLoop = true;
		String choice = null;
		while (purchaseLoop) {
			System.out.println("\n");
			System.out.println("1) Feed Money");
			System.out.println("2) Select Product");
			System.out.println("3) Finish Transaction");
			System.out.printf("Current Money Provided $" + "%.2f", vend.getBalance());
			System.out.println("\n");
			choice = in.nextLine();
			if (choice.equals("1")) {
				inputMoneyMenu();
			}
			if (choice.equals("2")) {
				System.out.println("What button would you like to push?");
				String choice2 = in.nextLine();
				choice2 = choice2.toUpperCase();
				vend.getSnack(choice2);
			}
			if (choice.equals("3")) {
				vend.finishTransaction();
				purchaseLoop = false;
			}
		}
		return choice;
	}

	public void inputMoneyMenu() {
		System.out.println("How much money would you like to enter? Whole dollars please.");
		try {
			String choice = in.nextLine();
			double checkNum = Double.parseDouble(choice);
			if (((checkNum * 100)) % 100 == 0 && checkNum > 0) {
				vend.cashIn(choice);
				System.out.printf("$" + "%.2f", vend.getBalance());
				System.out.println();
			} else {
				System.out.println("Please choose whole number.");
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("Please choose a whole number.");
			purchaseMenu();
		}
	}
}
