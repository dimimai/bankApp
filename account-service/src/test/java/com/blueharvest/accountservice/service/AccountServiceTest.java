package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.AccountType;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void createNewAccoount() {

        Customer customer  =   Customer.builder().id(1L).firstName("John").lastName("Doe").birthDate(new Date()).build();

        Account account = Account.builder().iban("NL12345").accountType(AccountType.current).balance(1000.0).customer(customer).build();

        Account newACcount = accountRepository.save(account);
        assertEquals(newACcount,account);
    }
}