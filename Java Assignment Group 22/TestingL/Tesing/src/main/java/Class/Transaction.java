/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author georg
 */
public class Transaction {

    public String transactionID;
    public String customerID;
    public double amount;
    private String transactionDate;
    public double accBalance;

    public Transaction(String transactionID, String customerID, double amount, double accBalance) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.amount = amount;
        this.transactionDate = dateTimeToString(LocalDateTime.now());
        this.accBalance = accBalance;
    }
    public Transaction(String transactionID, String customerID, double amount, double accBalance,LocalDateTime time) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.amount = amount;
        this.transactionDate = dateTimeToString(time);
        this.accBalance = accBalance;
    }
    private String dateTimeToString(LocalDateTime Time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        return Time.format(formatter);
    }
    
    public String getDate(){
        return this.transactionDate;
    };  
}
