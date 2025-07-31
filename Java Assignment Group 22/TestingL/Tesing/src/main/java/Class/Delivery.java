/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class Delivery {

    public String deliveryID;
    public String orderID;
    public String deliveryRunnerID;
    public Address deliveryAddress;

    public Delivery(String deliveryID, String orderID, String deliveryRunnerID, Address deliveryAddress) {
        this.deliveryID = deliveryID;
        this.orderID = orderID;
        this.deliveryRunnerID = deliveryRunnerID;
        this.deliveryAddress = deliveryAddress;
    }
}
