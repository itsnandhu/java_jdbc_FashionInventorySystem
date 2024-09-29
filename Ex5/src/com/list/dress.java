package com.list;

public class dress {

    private int p_No;
    private String category;
    private String colour;
    private float price;
    private String model;

    // Default constructor
    public dress() {
    }

    // Parameterized constructor
    public dress(int p_No, String category, String colour, float price, String model) {
        this.p_No = p_No;
        this.category = category;
        this.colour = colour;
        this.price = price;
        this.model = model;
    }

    public int getP_No() {
        return p_No;
    }

    public void setP_No(int p_No) {
        this.p_No = p_No;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "dress [p_No=" + p_No + ", category=" + category + ", colour=" + colour + ", price=" + price + ", model=" + model + "]";
    }
}
