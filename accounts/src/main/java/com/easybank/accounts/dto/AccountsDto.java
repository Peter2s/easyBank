package com.easybank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotEmpty(message = "Account Number can not be Empty")
    @Pattern(regexp = "(^$[0-9]){10}",message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type can not be Empty")
    private String accountType;

    @NotEmpty(message = "Branch Address can not be Empty")
    private String BranchAddress;
}
