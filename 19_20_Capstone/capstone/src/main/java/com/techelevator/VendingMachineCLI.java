package com.techelevator;

import com.techelevator.view.Menu;

import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachineCLI {
	//List<Inventory> inventoryList = new ArrayList<>();

	Scanner scan = new Scanner(System.in);

	VendingMachine vm1 = new VendingMachine();



	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	//Displayed to user 1) Display Items 2) Purchase Items 3) Exit 4) **Optional hidden menu item Sales Report
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	//Display menu of options
	//Array of Strings --> Purchase menu options: Feed Money, Select Product, Finish Transaction
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION= "Finish Transaction";
	//Display to the user
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
				//show location, name, and price
				//item available/show List
				System.out.println(vm1.getDisplayForItemsString());


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String purchaseChoices = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					//adding money
					if (purchaseChoices.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
						System.out.println("How much money do you want to add?");
						String moneyAdded = scan.nextLine();

						//TODO: Prevent the balance from being anything other than whole dollars
						Double doubleMoneyAdded = Double.parseDouble(moneyAdded);
						Double balanceInPenniesDouble = (doubleMoneyAdded*100);
						int balanceInPennies = balanceInPenniesDouble.intValue();
						if (((balanceInPennies % 10) != 0)){
							System.out.println("This machine only takes whole dollars.");
							break;
						}
							vm1.addMoney(doubleMoneyAdded);
							System.out.println("Your current balance is " + vm1.getBalance());
					}

					//select product
					if (purchaseChoices.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						System.out.println("Select a product: ");
						String selectedProduct = scan.nextLine(); //Calls a location of item

						if(vm1.items.get(selectedProduct) != null){
							vm1.purchaseItem(selectedProduct);
							System.out.println("You selected " + selectedProduct);
							//method for purchasing product --> call purchaseItem method
							//check balance
							//if balance is <=0 print error message
							//if balance is <= cost of item print error message
							// if item is sold out print error message
						} else {
							System.out.println("Selected item not applicable");
							break;
						}

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
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("Thank you for using Umbrella Corp.'s Vendo-Matic 600 -- GOODBYE");
				break;
			}
		}
	}
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
