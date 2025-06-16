package com.easybank.accounts.service;

import com.easybank.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - customer Dto object
     */
    void createAccount(CustomerDto customerDto);
    /**
     * @param String - customer mobileNumber
     */
    CustomerDto getAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount (String mobileNumber);
}
