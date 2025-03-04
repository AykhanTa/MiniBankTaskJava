package com.example.BankTask.dtos;

import lombok.Data;

@Data
public class TransactionDto {
    private String id;
    private String type;
    private float amount;
    private Boolean RefundStatus;
    private Long customerId;
    private String customerFullName;
    private String customerEmail;

    public TransactionDto(String id, String type, float amount, Long customerId, String customerName, String customerEmail) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.customerId = customerId;
        this.customerFullName = customerName;
        this.customerEmail = customerEmail;
    }

}
