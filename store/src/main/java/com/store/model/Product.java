package com.store.model;

public class Product {
    // Attributes for product
    int itemID;
    String name;
    float MSRP; //Manufacturer Suggested Retail Price
    float salePrice;
    int UPC; //Universal Product Code
    String shortDesc;
    String brandName;
    String size;
    String color;
    String gender;

    public Product(int itemID, String name, float MSRP, float salePrice, int UPC, String shortDesc, String brandName, String size, String color, String gender) {
        this.itemID = itemID;
        this.name = name;
        this.MSRP = MSRP;
        this.salePrice = salePrice;
        this.UPC = UPC;
        this.shortDesc = shortDesc;
        this.brandName = brandName;
        this.size = size;
        this.color = color;
        this.gender = gender;
    }

    public Product() {
        this.itemID = 0;
        this.name = "Null";
        this.MSRP = 0;
        this.salePrice = 0;
        this.UPC = 0;
        this.shortDesc = "This should never be read!";
        this.brandName = "Unbranded";
        this.size = "M";
        this.color = "Green";
        this.gender = "M";
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMSRP() {
        return MSRP;
    }

    public void setMSRP(float MSRP) {
        this.MSRP = MSRP;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getUPC() {
        return UPC;
    }

    public void setUPC(int UPC) {
        this.UPC = UPC;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString(){
        return "Product - itemId: " + itemID + "\t| Name: "+ name + "\t| MSRP: "+MSRP+"\t| Sale Price: "+salePrice+"\t| UPC: "+UPC+"\t| Short Description: "+shortDesc+"\t| Brand: "+brandName+"\t| Size: "+size+"\t| Color: "+color+"\t| Gender: "+gender;
    }
}
