package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    Map <String, Inventory> items = new HashMap<String, Inventory>();

    public void updateInventoryList() {
        try (Scanner fileReader = new Scanner(new File("Example/VendingMachine.txt"))) {
            //while a line exists, keep reading
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();
                //go up to each | and put the values into a new array
                String[] lineToArray = line.split("\\|");

                for (int i = 0; i < lineToArray.length; i++) {
                    String location = lineToArray[0];
                    String name = lineToArray[1];
                    double price = Double.valueOf(lineToArray[2]);
                    String inventoryType = lineToArray[3];
                    if (inventoryType.equals("Chips")) {
                        Chips chips = new Chips(location, name, 5, price);
                        items.put(location, chips);
                    }
                    if (inventoryType.equals("Candy")) {
                        Candy candy = new Candy(location, name, 5, price);
                        items.put(location, candy);
                    }
                    if (inventoryType.equals("Drink")) {
                        Drink drink = new Drink(location, name, 5, price);
                    }
                    if (inventoryType.equals("Gum")) {
                        Gum gum = new Gum(location, name, 5, price);
                    } else return;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();


        }
    }


}
