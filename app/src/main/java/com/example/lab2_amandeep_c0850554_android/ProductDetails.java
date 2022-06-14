package com.example.lab2_amandeep_c0850554_android;

public class ProductDetails {
    private int id;
    private  String Pro_Name, Pro_Description;
    private double Pro_Price;

    public ProductDetails(int id, String Pro_Name, String Pro_Description, double Pro_Price) {
        this.id = id;
        this.Pro_Name = Pro_Name;
        this.Pro_Description = Pro_Description;
        this.Pro_Price = Pro_Price;
    }

    public int getId() {
        return id;
    }

    public String getPro_Name() {
        return Pro_Name;
    }

    public String getPro_Description() {
        return Pro_Description;
    }



    public double getPro_Price() {
        return Pro_Price;
    }
}
