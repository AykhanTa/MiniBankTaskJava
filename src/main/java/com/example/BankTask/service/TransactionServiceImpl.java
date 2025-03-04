package com.example.BankTask.service;

import com.example.BankTask.dtos.TransactionDto;
import com.example.BankTask.map.TransactionMapper;
import com.example.BankTask.model.Customer;
import com.example.BankTask.model.Transaction;
import com.example.BankTask.repository.CustomerRepository;
import com.example.BankTask.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> getTransactions() {
        List<Transaction> transactionList = transactionRepository.findAll();
        return transactionMapper.toDtoList(transactionList);
    }

    @Override
    public void addTransaction(String type, Float amount, Long customer_id) {
        Customer existCustomer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new IllegalArgumentException("Customer can't find"));
        if (type.equals("TopUp")) {
            existCustomer.setBalance(existCustomer.getBalance() + amount);
        }
        if (type.equals("Purchase")) {
            existCustomer.setBalance(existCustomer.getBalance() - amount);
        }

        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setCustomer(existCustomer);

        transactionRepository.save(transaction);
        customerRepository.save(existCustomer);
    }

    @Override
    public void refundTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction can't find"));
        if (transaction.getRefundStatus() == true) {
            throw new IllegalStateException("Refund Status is also True");
        }
        Customer existCustomer = customerRepository.findById(transaction.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer can't find"));
        if (transaction.getType().equals("TopUp")) {
            existCustomer.setBalance(existCustomer.getBalance() - transaction.getAmount());
        }
        if (transaction.getType().equals("Purchase")) {
            existCustomer.setBalance(existCustomer.getBalance() + transaction.getAmount());
        }
        transaction.setRefundStatus(true);
        transactionRepository.save(transaction);
        customerRepository.save(existCustomer);
    }

    @Override
    public TransactionDto getTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction can't find"));
        return transactionMapper.toDto(transaction);
    }
}
