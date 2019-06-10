package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.exception.AccountNotFoundException;
import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createNewAccoount(Account account) {

        return accountRepository.save(account);
    }

    public Long getAccountId(Long customerId) {

        Account account = accountRepository.findByCustomerId(customerId);

        if (account == null) {
            throw new AccountNotFoundException("account does not exist");
        }

        return account.getId();
    }


}
