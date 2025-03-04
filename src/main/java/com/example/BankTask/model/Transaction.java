package com.example.BankTask.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Transaction {
    @Id
    private String id;
    private String Type;
    private float Amount;
    private Boolean RefundStatus=false;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Transaction(){
        this.id= UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
    }
}
