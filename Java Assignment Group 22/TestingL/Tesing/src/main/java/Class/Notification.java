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
public class Notification {

    public String notificationID;
    public String userID;
    public String notificationContent;
    private String time;

    public Notification(String notificationID, String userID, String notificationContent) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.notificationContent = notificationContent;
        this.time = dateTimeToString(LocalDateTime.now());
    }
    private String dateTimeToString(LocalDateTime Time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
        return Time.format(formatter);
    }
    
    public String getDate(){
        return this.time;
    };  
}

