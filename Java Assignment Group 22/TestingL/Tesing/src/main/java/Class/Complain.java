/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class Complain {

    public String complainID;
    public String customerID;
    public String orderID;
    public String complainContent;
    public String status;

    public Complain(String complainID, String customerID, String orderID, String complainContent, String status) {
        this.complainID = complainID;
        this.customerID = customerID;
        this.orderID = orderID;
        this.complainContent = complainContent;
        this.status = status;
    }
}
