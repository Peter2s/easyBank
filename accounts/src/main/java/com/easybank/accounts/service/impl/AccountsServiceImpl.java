package com.easybank.accounts.service.impl;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Account;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.mapper.AccountsMapper;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repo.AccountsRepo;
import com.easybank.accounts.repo.CustomersRepo;
import com.easybank.accounts.service.IAccountsService;
import com.easybank.accounts.constants.AccountsConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepo accountsRepo;
    private CustomersRepo customersRepo;
    /**
     * @param customerDto - CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer =  customersRepo.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with the same Mobile Number "
            + customerDto.getMobileNumber());
        }
        customersRepo.save(customer);

        accountsRepo.save(createNewAccount(customer));

    }

    /**
     * @param mobileNumber
     * @return account based on mobile number
     */
    @Override
    public CustomerDto getAccount(String mobileNumber) {
        Customer customer = customersRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        Account account = accountsRepo.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Account","CustomerId",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account,new AccountsDto()));
        return  customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null){
            Account account = accountsRepo.findById(accountsDto.getAccountNumber())
                    .orElseThrow(()-> new ResourceNotFoundException("Account","accountNumber",accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto,account);
            account = accountsRepo.save(account);

            Long customerId = account.getCustomerId();
            Customer customer =  customersRepo.findById(customerId)
                    .orElseThrow(()-> new ResourceNotFoundException("Customer","customerId",customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto,customer);
            customersRepo.save(customer);
            return true;
        }
        return  false;


    }

    /**
     * @param mobileNumber
     * @return boolean True if account deleted successful
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customersRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        accountsRepo.deleteByCustomerId(customer.getCustomerId());
        customersRepo.deleteById(customer.getCustomerId());
        return true;
    }

    private Account createNewAccount(Customer customer){
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        Long accountNumber = 10000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;

    }
}
