package com.example.BankTask.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerDto {
    private Long Id;

    private String FullName;
    private String Email;
    private LocalDate Birthdate;
    private float Balance;
}
