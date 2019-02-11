package com.techelevator;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	DateTimeFormatter dft = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	DecimalFormat twoDForm = new DecimalFormat("#0.00");

	protected double balance = 0.00;
	private Map<String, Snack> map;
	private Map<String, Integer> salesMap;
	double totalSales = 0.00;

	public VendingMachine() {
		map = new LinkedHashMap<String, Snack>();
		salesMap = new LinkedHashMap<String, Integer>();
		makeSnacks();
	}

	public void cashIn(String userMoney) {
		balance += Double.parseDouble(userMoney);
		log(dft.format(now) + " " + "FEED MONEY" + " " + twoDForm.format(balance) + "\n");
	}

	public void makeSnacks() {
		File input = new File("vendingmachine.csv");
		try (Scanner fileScanner = new Scanner(input)) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] array = line.split("\\|");
				String location = array[0];
				String name = array[1];
				String sound = array[3];
				String priceString = array[2];
				double price = Double.parseDouble(priceString);
				if (sound.equals("Chip")) {
					Chips newChip = new Chips(location, name, sound, price);
					salesMap.put(location, newChip.totalSold);
					map.put(location, newChip);
				}
				if (sound.equals("Gum")) {
					Gum newGum = new Gum(location, name, sound, price);
					salesMap.put(location, newGum.totalSold);
					map.put(location, newGum);
				}
				if (sound.equals("Candy")) {

					Candy newCandy = new Candy(location, name, sound, price);
					salesMap.put(location, newCandy.totalSold);
					map.put(location, newCandy);
				}
				if (sound.equals("Drink")) {
					Drink newDrink = new Drink(location, name, sound, price);
					salesMap.put(location, newDrink.totalSold);
					map.put(location, newDrink);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Input File Not Found");
			e.printStackTrace();
		}
	}

	public double getBalance() {
		return balance;
	}

	public Map<String, Snack> getMap() {
		return map;
	}

	public boolean hasEnoughMoney(Snack snack) {
		return snack.price < balance;
	}

	public boolean isAvailable(Snack snack) {
		return snack.quantity > 0;
	}

	public void finishTransaction() {
		log(dft.format(now) + " " + "GIVE CHANGE" + " " + twoDForm.format(balance) + "\n");
		int quarter = 0;
		int dime = 0;
		int nickel = 0;
		int remainder = 0;
		balance *= 100;
		int newBalance = (int) balance;
		quarter = newBalance / 25;
		remainder = newBalance % 25;
		newBalance = remainder;
		dime = newBalance / 10;
		remainder = newBalance % 10;
		newBalance = remainder;
		nickel = newBalance / 5;
		remainder = newBalance % 5;
		balance = 0;
		System.out
				.println("Your change is " + quarter + " quarters, " + dime + " dimes, and " + nickel + " nickels.\n");
	}

	public void getSnack(String userInput) {
		if (!(map.containsKey(userInput))) {
			System.out.println("Invalid Entry. Try Again.");
		} else if (map.containsKey(userInput) && hasEnoughMoney(map.get(userInput))
				&& isAvailable(map.get(userInput))) {
			System.out.println();
			balance -= map.get(userInput).price;
			int numberOfItems = map.get(userInput).getQuanity() - 1;
			map.get(userInput).setQuantity(numberOfItems);
			updateSalesMap(userInput);
			totalSales += map.get(userInput).price;
			saleReport();
			log(dft.format(now) + " " + map.get(userInput).getName() + " " + twoDForm.format(balance) + "\n");
			System.out.println(map.get(userInput).name + "\n" + map.get(userInput).getSound() + "\n");
		} else if (!(hasEnoughMoney(map.get(userInput)))) {
			System.out.println("Insufficient funds! Add more money.\n");
		} else if (!(isAvailable(map.get(userInput)))) {
			System.out.println("Sold Out.\n");
		}
	}

	public void updateSalesMap(String userInput) {
		salesMap.put(userInput, (salesMap.get(userInput) + 1));
	}

	public void log(String log) {
		File logger = new File("Log.txt");
		try {
			if (logger.exists() == false) {
				logger.createNewFile();
			}
			PrintWriter out = new PrintWriter(new FileWriter(logger, true));
			out.append(log);
			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}
	}

	public void saleReport() {
		File sales = new File("Sales_Report.txt");
		String output = null;
		try (FileWriter writer = new FileWriter(sales)) {
			for (String key : salesMap.keySet()) {
				output = map.get(key).name + "|" + salesMap.get(key);
				writer.write(output + "\n");
			}
			writer.write("***TOTAL SALES***   $" + twoDForm.format(totalSales));
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}
	}

}
