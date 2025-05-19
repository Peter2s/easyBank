package com.easybank.accounts.repo;

import com.easybank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long aLong);
}
