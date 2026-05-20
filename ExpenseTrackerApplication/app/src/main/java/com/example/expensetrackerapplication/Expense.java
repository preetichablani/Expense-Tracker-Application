package com.example.expensetrackerapplication;

public class Expense {
    int id;
    String title,category,amount,date;
    public Expense(int id, String title, String amount, String category, String date){
        this.id=id;
        this.title=title;
        this.amount=amount;
        this.category=category;
        this.date=date;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}