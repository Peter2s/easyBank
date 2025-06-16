package com.easybank.accounts.controller;

import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.IAccountsService;
import com.easybank.accounts.constants.AccountsConstants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {
    private IAccountsService accountsService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto =  accountsService.getAccount(mobileNumber);
        return  ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @PutMapping("/update")
    public  ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto){
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated){
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }
}
