package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.AccountType;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    AccountService accountService;

    @Test
    public void whenSaveAccountItReturnsAccount() {
        Customer customer = Customer.builder().id(1L).firstName("John").lastName("Doe").birthDate(new Date()).build();

        Account account = Account.builder().iban("NL12345").accountType(AccountType.CURRENT).balance(1000.0).customer(customer).build();

        when(accountRepository.save(any(Account.class))).thenReturn(new Account());

        Account created = accountService.createNewAccoount(account);

        assertThat(created.getId()).isSameAs(account.getId());
    }

}