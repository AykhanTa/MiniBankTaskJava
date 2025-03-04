package com.example.BankTask.map;

import com.example.BankTask.dtos.CustomerDto;
import com.example.BankTask.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customers);
}
