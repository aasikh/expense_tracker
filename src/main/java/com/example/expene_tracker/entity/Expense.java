package com.example.expene_tracker.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name="expenses")

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double amount ;

    @Column(nullable = false)
    private String category;

    private String description;

    private LocalDateTime date;

    //gatter and setter

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


}
