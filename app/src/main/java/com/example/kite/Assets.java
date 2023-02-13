package com.example.kite;

public class Assets {


    String name;
    double amount;
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public Assets(){}
    public Assets(String name,double amount) {

        this.amount = amount;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
