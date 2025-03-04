package com.example.BankTask.dtos;

import lombok.Data;

@Data
public class TransactionCreateDto {
    private String Type;
    private float Amount;
}
