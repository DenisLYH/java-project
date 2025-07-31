/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class Menu {

    public String vendorID;
    public String itemID;
    public String itemName;
    public double price;
    public String status;//available,outOfStock,Removed

    public Menu(String vendorID, String itemID, String itemName, double price) {
        this.vendorID = vendorID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.status = "Available";
    }
}
