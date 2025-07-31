/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class User {

    public String userID;
    private String username;
    private String password;
    public String role;
    private String phoneNum;
    private Address address;

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Address getAddress() {
        return address;
    }
    
    public User(String userID, String username, String password, String role,String phoneNum,Address address) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    
    public User(String userID, String username, String password, String role, String phoneNum) {
        this(userID, username, password, role, phoneNum, null); 
    }
    
    /*public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' + 
                '}';
    }*/
}
