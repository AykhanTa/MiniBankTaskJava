package com.example.BankTask.service;

import com.example.BankTask.dtos.CustomerCreateDto;
import com.example.BankTask.dtos.CustomerDto;
import com.example.BankTask.dtos.PurchaseDto;
import com.example.BankTask.dtos.TopUpBalanceDto;
import com.example.BankTask.map.CustomerMapper;
import com.example.BankTask.model.Customer;
import com.example.BankTask.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final TransactionService transactionService;
    private final CustomerMapper customerMapper;

    @Override
    public void addCustomer(CustomerCreateDto customerCreateDto) {
        Optional<Customer> existCustomer = customerRepository.findCustomerByEmail(customerCreateDto.getEmail());
        if (existCustomer.isPresent()) {
            throw new IllegalStateException("Customer already exist");
        }
        Customer customer = new Customer();
        customer.setEmail(customerCreateDto.getEmail());
        customer.setFullName(customerCreateDto.getFullName());
        customer.setBirthdate(customerCreateDto.getBirthdate());
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, String email) {
        Optional<Customer> existCustomer = customerRepository.findById(Long.valueOf(id));
        if (!existCustomer.isPresent()) {
            throw new IllegalStateException("Customer can't be found");
        }
        if (!email.isEmpty() && !email.equals(existCustomer.get().getEmail())) {
            Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
            if (customer.isPresent()) {
                throw new IllegalStateException("Customer already exist");
            }
            existCustomer.get().setEmail(email);
        }
        customerRepository.save(existCustomer.get());
    }

    @Override
    public void deleteCustomer(Long id) {
        Optional<Customer> existCustomer = customerRepository.findById(Long.valueOf(id));
        if (!existCustomer.isPresent()) {
            throw new IllegalStateException("Customer can't be found");
        }
        customerRepository.deleteById(id);
    }

    public List<CustomerDto> getCustomers() {
        List<Customer> customersList = customerRepository.findAll();
        return customerMapper.toDtoList(customersList);
    }

    public CustomerDto getCustomerById(Long id) {
        return customerMapper.toDto(customerRepository.findById(id).get());
    }

    public Customer getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findCustomerByEmail(email);
        if (!customer.isPresent()) {
            throw new IllegalStateException("Customer can't be found");
        }
        return customer.get();
    }

    @Override
    public void topUpMoney(TopUpBalanceDto topUpBalanceDto) {
        Customer existCustomer = customerRepository.getById(topUpBalanceDto.getId());
        if (existCustomer == null) {
            throw new IllegalStateException("Customer can't be found");
        }
        transactionService.addTransaction("TopUp", topUpBalanceDto.getAmount(), existCustomer.getId());
    }

    @Override
    public void purchaseMoney(PurchaseDto purchaseDto) {
        Customer existCustomer = customerRepository.findById(purchaseDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Customer can't find"));
        transactionService.addTransaction("Purchase", purchaseDto.getAmount(), existCustomer.getId());
        customerRepository.save(existCustomer);
    }


}
