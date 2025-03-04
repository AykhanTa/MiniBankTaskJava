package com.example.BankTask.controller;

import com.example.BankTask.dtos.TransactionDto;
import com.example.BankTask.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<TransactionDto> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("{id}")
    public TransactionDto getTransaction(@PathVariable String id) {
        return transactionService.getTransaction(id);
    }

    @PostMapping("refundTransaction/{transactionId}")
    public void refundTransaction(@PathVariable String transactionId) {
        transactionService.refundTransaction(transactionId);
    }
}
