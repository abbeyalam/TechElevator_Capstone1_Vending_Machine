package com.techelevator;

import com.techelevator.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VendingMachineCLI {
	List<Inventory> inventoryList = new ArrayList<>();


	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {

		this.menu = menu;


		//populateChipArray
		//"mock Objects"
		//public Chips(String chipName, int chipAmount, double chipPrice){
		inventoryList.add(new Chips("A1","Potato Crisps",5,3.05));
		inventoryList.add(new Chips("A2","Stackers",5,1.45));
		inventoryList.add(new Chips("A3","Grain Waves",5,2.75));
		inventoryList.add(new Chips("A4","Cloud Popcorn",5,3.65));

		//populateCandyArray
		inventoryList.add(new Candy("B1","Moonpie",5,1.80));
		inventoryList.add(new Candy("B2","Cowtales",5, 1.50));
		inventoryList.add(new Candy("B3","Wonka Bar",5, 1.50));
		inventoryList.add(new Candy("B4","Crunchie",5, 1.75));

		//populateDrinkArray
		inventoryList.add(new Drink("C1","Cola",5, 1.25));
		inventoryList.add(new Drink("C2","Dr. Salt",5, 1.50));
		inventoryList.add(new Drink("C3","Mountain Melter", 5, 1.50));
		inventoryList.add(new Drink("C4","Heavy",5, 1.50));

		//populateGumArray
		inventoryList.add(new Gum("D1","U-Chews",5, .85));
		inventoryList.add(new Gum("D2","Little League Chew",5, .95));
		inventoryList.add(new Gum("D3","Chiclets",5, .75));
		inventoryList.add(new Gum("D4","Triplemint",5, .75));


	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				//not Amount, but show location, name, and price
				//item available/show List

				for (int i = 0; i<inventoryList.size(); i++){
					System.out.println(inventoryList.get(i).getLocation() + " " +inventoryList.get(i).getName() + " " + inventoryList.get(i).getPrice());
					//if amount <=0, display SOLD OUT
				}

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
