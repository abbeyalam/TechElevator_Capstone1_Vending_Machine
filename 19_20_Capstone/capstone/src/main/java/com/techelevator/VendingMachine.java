package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine {
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    Map <String, Inventory> items = new TreeMap<String, Inventory>();
    private double balance = 0;
    private String auditFileName = "Log.txt";

    public static void auditFile(){

    }

    //Method called log can return void
    //Take a parameter of message you want to log
    //Log wants to have how much money was spent
    //Entry for everytime we fed money, date time and new balance
    //Opens the file for appending (FILE IO)
    //Write the line that was passed into parameter and close it
    //Try w/resources or make sure you close it (file you opened for writing)
    //Test method to make sure it works
    //Build String, Call method

    public String returnChange(){

        //convert remaining
        int quarter = 25;
        int dime = 10;
        int nickel = 5;
        int remainder = 0;
        int balanceInPennies = (int)(balance*100);
        double amountOfChangeBeingReturned = balance;
        int returnedQuarters=0;
        int returnedDimes=0;
        int returnedNickels=0;

        if(balanceInPennies >0){
            returnedQuarters = balanceInPennies / quarter;
            balanceInPennies = balanceInPennies % quarter;
        }

        if(balanceInPennies >0) {
            returnedDimes = balanceInPennies / dime;
            balanceInPennies = balanceInPennies % dime;
        }

        if(balanceInPennies >0) {
            returnedNickels = balanceInPennies / nickel;
            balanceInPennies = balanceInPennies % nickel;
        }

        balance = balanceInPennies;

        //TODO: call writeToLogFile for the GIVE CHANGE scenario\
        String outputString = "GIVE CHANGE: $" + decimalFormat.format(amountOfChangeBeingReturned) +" $" + getBalance();
        writeToLogFile(outputString);

        return "Your change is " + returnedQuarters + " quarters, " + " " + returnedDimes + " dimes," + "" + returnedNickels + " nickels";
    }


    public double getBalance(){
        return Double.parseDouble(decimalFormat.format(balance));
    }

    public double addMoney(double amountToAdd){
        //Only accept whole dollars
        //TODO: call writeToLogFile for the FEED MONEY scenario

        balance +=amountToAdd;

        String outputString = "FEED MONEY: $" + amountToAdd + " $" + getBalance();
        writeToLogFile(outputString);

        return balance;
    }


    public VendingMachine(){
        updateInventoryList();
    }

    public void updateInventoryList() {
        try (Scanner fileReader = new Scanner(new File("ExampleFiles/VendingMachine.txt"))) {
            //while a line exists, keep reading
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();
                //go up to each | and put the values into a new array
                String[] lineToArray = line.split("\\|");

                    String location = lineToArray[0];
                    String name = lineToArray[1];
                    double price = Double.valueOf(lineToArray[2]);
                    String inventoryType = lineToArray[3];
                    if (inventoryType.equals("Chip")) {
                        Chips chips = new Chips(location, name, 5, price);
                        items.put(location, chips);
                    }
                    if (inventoryType.equals("Candy")) {
                        Candy candy = new Candy(location, name, 5, price);
                        items.put(location, candy);
                    }
                    if (inventoryType.equals("Drink")) {
                        Drink drink = new Drink(location, name, 5, price);
                        items.put(location, drink);
                    }
                    if (inventoryType.equals("Gum")) {
                        Gum gum = new Gum(location, name, 5, price);
                        items.put(location, gum);
                    }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }


    }
    public String getDisplayForItemsString(){
        String display = "";
        for (Inventory item : items.values()){
            if (item.getAmount() <= 0) {
                display += item.getLocation() + " " + item.getName() + " " + item.getPrice() + " " + "Item SOLD OUT" + "\n" ;
            }
            else {
                display += item.getLocation() + " " + item.getName() + " " + item.getPrice() + " " + item.getAmount() + "\n" ; //add getPrice and add Location
            }
        }
        return display;
    }

    //purchaseItem Method
    public String purchaseItem(String selectedLocation){
        if (balance <= 0){
            System.out.println("Come back with some money");
        }
        Inventory selectedItem = items.get(selectedLocation);
       // if item costs more than balance --> return error message
       if (balance <= selectedItem.getPrice()){
            System.out.println("You need more money");
        }
       //if item is out of stock  ( get amount) --> return error message
        if (selectedItem.getAmount() < 1){
            System.out.println("SOLD OUT");
        }
        if (balance >= selectedItem.getPrice()){
            balance = balance - selectedItem.getPrice();
            //how do we update subtracting the amount?
            selectedItem.purchaseOneItem();
            System.out.println(selectedItem.makeNoise());
        }

        String outputString = selectedItem.getName() + " " + selectedItem.getLocation() + " $"
                + selectedItem.getPrice() + " $" + getBalance();
        writeToLogFile(outputString);

        return selectedItem.getName();
    }

    public void writeToLogFile(String outputString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("------------------>" + currentDate + outputString);

        //TODO: Open file

        //TODO: write to log.txt "rolling write" (append)

        //TODO: close file
    }


}
