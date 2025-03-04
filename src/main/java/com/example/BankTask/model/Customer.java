package com.example.BankTask.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long Id;

    private String FullName;
    private String Email;
    private LocalDate Birthdate;
    private float Balance=100;

    @OneToMany(mappedBy = "customer")
    private List<Transaction> Transactions;

    public Customer(String fullName, String email, LocalDate birthdate, float balance) {
        FullName = fullName;
        Email = email;
        Birthdate = birthdate;
        Balance = balance;
    }

}
