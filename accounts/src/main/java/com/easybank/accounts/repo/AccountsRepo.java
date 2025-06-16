package com.easybank.accounts.repo;

import com.easybank.accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepo extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long aLong);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long aLong);
}
