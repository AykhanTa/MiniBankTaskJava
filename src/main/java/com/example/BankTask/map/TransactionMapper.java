package com.example.BankTask.map;

import com.example.BankTask.dtos.TransactionDto;
import com.example.BankTask.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.email", target = "customerEmail")
    @Mapping(source = "customer.fullName", target = "customerFullName")
    @Mapping(source = "transaction.refundStatus",target = "refundStatus")

    TransactionDto toDto(Transaction transaction);

    List<TransactionDto> toDtoList(List<Transaction> transactions);

}
