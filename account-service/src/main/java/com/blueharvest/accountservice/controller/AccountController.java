package com.blueharvest.accountservice.controller;


import com.blueharvest.accountservice.service.AccountService;
import com.blueharvest.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

   private AccountService accountService;
   private CustomerService customerService;

   @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }
}
