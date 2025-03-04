package com.example.BankTask.service;


import com.example.BankTask.dtos.CustomerCreateDto;
import com.example.BankTask.dtos.CustomerDto;
import com.example.BankTask.dtos.PurchaseDto;
import com.example.BankTask.dtos.TopUpBalanceDto;
import com.example.BankTask.model.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerCreateDto customerCreateDto);

    void updateCustomer(Long id, String email);

    void deleteCustomer(Long id);

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    void topUpMoney(TopUpBalanceDto topUpBalanceDto);

    void purchaseMoney(PurchaseDto purchaseDto);

}
