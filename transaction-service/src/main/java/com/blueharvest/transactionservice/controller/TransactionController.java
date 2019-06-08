package com.blueharvest.transactionservice.controller;

import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(value = "/transactions",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> transactionCreation(@RequestBody Transaction transaction){

        Transaction newTransaction = transactionService.createNewTransaction(transaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

}
