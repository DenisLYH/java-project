/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author georg
 */
public class Order {

    public String orderID;
    public String customerID;
    public String vendorID;
    public List<OrderItem> items = new ArrayList<>();
    public String orderType;
    public Double deliveryFee;
    public Double totalPrice;
    public String orderStatus;
    public Address deliveryAddress;
    public String deliveryID;
    public String orderDate;
    
    public Order(String orderID, String customerID, String vendorID, ArrayList items, String orderType,Double deliveryFee, Double totalPrice, String orderStatus, Address deliveryAddress, String deliveryID,LocalDateTime orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.vendorID = vendorID;
        this.items = items;
        this.orderType = orderType;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.deliveryID = deliveryID;
        this.orderDate = dateTimeToString(orderDate);
    }
    public Order(String orderID, String customerID, String vendorID, ArrayList items, String orderType,Double deliveryFee, Double totalPrice, String orderStatus, Address deliveryAddress, String deliveryID) {
        this(orderID, customerID, vendorID, items, orderType, deliveryFee, totalPrice,
             orderStatus, deliveryAddress, deliveryID, LocalDateTime.now());
    }
    
    private String dateTimeToString(LocalDateTime Time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        return Time.format(formatter);
    }
    public String GetDate(){
        return this.orderDate;
    }
}
