package com.easybank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name can not be Empty")
    @Size(min = 5 , max = 30,message = "the length of customer name should be between 5 and 30 charter" )
    private String name;

    @NotEmpty(message = "Email can not be Empty")
    @Email(message = "Email Address should be valid email")
    private String email;

    @Pattern(regexp = "(^$[0-9]){11}", message = "mobile number should be valid mobile number ")
    private String mobileNumber;
    private AccountsDto accountsDto;
}
