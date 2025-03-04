package com.example.BankTask.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CustomerCreateDto {
    private String FullName;
    private String Email;
    private LocalDate Birthdate;
}
