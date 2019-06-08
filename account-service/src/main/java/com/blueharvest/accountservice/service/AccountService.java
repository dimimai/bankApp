package com.blueharvest.accountservice.service;

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

    public Account createNewAccoount(Account account){

        Account newAccount = accountRepository.save(account);

        return newAccount;
    }
}
