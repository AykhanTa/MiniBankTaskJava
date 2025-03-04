package com.example.BankTask.repository;

import com.example.BankTask.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c Where c.Email=?1")
    Optional<Customer> findCustomerByEmail(String email);

}
