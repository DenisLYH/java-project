/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testing;

/**
 *
 * @author georg
 */
public class Review {

    public String reviewID;
    public String customerID;
    public String entityType;
    public String reviewedEntityID;
    public int rating;
    public String comment;

    public Review(String reviewID, String customerID, String entityType, String reviewedEntityID, int rating, String comment) {
        this.reviewID = reviewID;
        this.customerID = customerID;
        this.entityType = entityType;
        this.reviewedEntityID = reviewedEntityID;
        this.rating = rating;
        this.comment = comment;
    }
}
