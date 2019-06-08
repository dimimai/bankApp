package com.blueharvest.accountservice.controller;


import com.blueharvest.accountservice.TransactionServiceProxy;
import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.model.TransactionBean;
import com.blueharvest.accountservice.service.AccountService;
import com.blueharvest.accountservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

   private AccountService accountService;
   private CustomerService customerService;
   private TransactionServiceProxy proxy;

   @Autowired
    public AccountController(AccountService accountService, CustomerService customerService,
                             TransactionServiceProxy proxy) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.proxy = proxy;
    }

    @PostMapping(value = "/customers/{customerId}/accounts",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> retrieveAccount(@PathVariable(value = "customerId") Long customerId,
                                                  @RequestBody Account account) {

       Customer customer = customerService.getCustomerById(customerId);
       account.setCustomer(customer);

       Account newAccount = accountService.createNewAccoount(account);

       if(account.getBalance() !=0 ){
           TransactionBean newTransaction = TransactionBean.builder()
                   .accountId(newAccount.getId())
                   .amount(account.getBalance())
                   .build();
           proxy.transactionCreation(newTransaction);

       }

       return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }
}
