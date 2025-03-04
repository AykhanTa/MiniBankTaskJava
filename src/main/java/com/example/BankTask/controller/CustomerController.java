package com.example.BankTask.controller;

import com.example.BankTask.dtos.CustomerCreateDto;
import com.example.BankTask.dtos.CustomerDto;
import com.example.BankTask.dtos.PurchaseDto;
import com.example.BankTask.dtos.TopUpBalanceDto;
import com.example.BankTask.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("topUpMoney")
    public void topUpMoney(@RequestBody TopUpBalanceDto topUpBalanceDto) {
        customerService.topUpMoney(topUpBalanceDto);
    }

    @PostMapping("purchase")
    public void purchase(@RequestBody PurchaseDto purchaseDto) {
        customerService.purchaseMoney(purchaseDto);
    }

    @PostMapping("updateCustomer/{id}")
    public void updateCustomer(@PathVariable long id, @RequestParam(required = false) String email) {
        customerService.updateCustomer(id, email);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        customerService.addCustomer(customerCreateDto);
    }

    @DeleteMapping("deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }


}
