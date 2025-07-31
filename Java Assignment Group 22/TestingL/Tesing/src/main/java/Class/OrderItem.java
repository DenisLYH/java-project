/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class OrderItem {

    public String menuID;
    public String itemName;
    public int quantity;
    public double price;

    public OrderItem(String itemID, String itemName, int quantity, double price) {
        this.menuID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}
