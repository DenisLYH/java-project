/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class Address {

    public String houseNumber;
    public String streetName;
    public String residentialArea;
    public String postalCode;
    public String city;
    public String state;

    public Address(String houseNumber, String streetName, String residentialArea, String postalCode, String city, String state) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.residentialArea = residentialArea;
        this.postalCode = postalCode;
        this.city = city;
        this.state = state;
    }
    
    public String ToString(){
        return houseNumber +","+ streetName +","+ residentialArea +","+ postalCode +","+ city +","+ state;
    }
}
