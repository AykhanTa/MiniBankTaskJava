package com.example.BankTask.service;

import com.example.BankTask.dtos.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getTransactions();

    void addTransaction(String type, Float amount, Long customerId);

    void refundTransaction(String transactionId);

    TransactionDto getTransaction(String transactionId);
}
