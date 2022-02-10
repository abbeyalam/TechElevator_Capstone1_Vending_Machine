package com.techelevator;

import java.math.BigDecimal;

public abstract class Inventory {
    //instance variables
    private String location;
    private String name;
    private int amount;
    private double price;

    //getters
    public String getLocation(){return this.location;}
    public String getName(){return this.name;}
    public int getAmount(){return this.amount;}
    public double getPrice(){return this.price;}

    //constructor
    public Inventory (String location, String name, int amount, double price){
        this.location = location;
        this.name = name;
        this.amount = amount;
        this. price = price;
    }

}
