package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createNewTransaction(Transaction transaction){

        Transaction newTransaction = transactionRepository.save(transaction);

        return newTransaction;
    }

    public List<Transaction> getAllTransactionsById(Long accountId) {

        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        return transactions;
    }


}