package com.techelevator;

import com.techelevator.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {
	//List<Inventory> inventoryList = new ArrayList<>();

	Scanner scan = new Scanner(System.in);

	VendingMachine vm1 = new VendingMachine();



	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	//Display menu of options
	//Array of Strings --> Purchase menu options: Feed Money, Select Product, Finish Transaction
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION= "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY , PURCHASE_MENU_OPTION_SELECT_PRODUCT , PURCHASE_MENU_OPTION_FINISH_TRANSACTION };




	private Menu menu;

	public VendingMachineCLI(Menu menu) {

		this.menu = menu;


	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				//not Amount, but show location, name, and price
				//item available/show List
				System.out.println(vm1.getDisplayForItemsString());


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String purchaseChoices = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoices.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
						System.out.println("How much money do you want to add?");
						String moneyAdded = scan.nextLine(); //make sure to take in account  moneyAdded % 5
						Double doubleMoneyAdded = Double.parseDouble(moneyAdded);
						vm1.addMoney(doubleMoneyAdded);
						System.out.println("Your current balance is " + vm1.getBalance());
					}
					if (purchaseChoices.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						System.out.println("Select a product: ");
						String selectedProduct = scan.nextLine(); //Calls a location of item
						vm1.purchaseItem(selectedProduct);
						System.out.println("You selected " + selectedProduct);
						//method for purchasing product --> call purchaseItem method
						//check balance
						//if balance is <=0 print error message
						//if balance is <= cost of item print error message
						// if item is sold out print error message
					}
					if (purchaseChoices.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
						System.out.println(vm1.getBalance());
						System.out.println(vm1.returnChange());
						// Call returnChange balance
						break;
					}

					// if choice.equal("Finish Transaction") --> break while loop and exit
					//else choice.equal("Exit") --> break;
					vm1.getBalance(); //Check balance
					//
				}
			}
		}
	}
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
