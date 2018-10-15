package com.store.model;

public class Customer {
    int id;
    String fname;
    String lname;
    String username;
    String email;

    public Customer(int id, String fname, String lname, String username, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public Customer(int id) {
        this.id = id;
    }

    public Customer() {
        this.id = 0;
        this.fname = "Null";
        this.lname = "Null";
        this.username = "Null";
        this.email = "Null";
    }

    public Customer(String fname, String lname, String username, String email) {
        this.id = 0;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "Customer - id: " + id + "\t| First Name: "+ fname + "\t| Last Name: "+lname+"\t| Username: "+username+"\t| Email: "+email;
    }
}
