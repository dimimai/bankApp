package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void createNewTransaction() {

        Transaction transactionJson = Transaction.builder().accountId(1000L).amount(1000.0).build();

        Transaction transaction = transactionRepository.save(transactionJson);
        assertEquals(transaction,transactionJson);
        assertEquals(transactionRepository.count(),1);

    }

    @Test
    public void getAllTransactionsById() {
        final Long accountId = 1000L;

        Transaction transactionOne = Transaction.builder().amount(1000.0).accountId(accountId).build();
        Transaction transactionTwo = Transaction.builder().amount(1000.0).accountId(accountId).build();

        transactionRepository.save(transactionOne);
        transactionRepository.save(transactionTwo);

        List<Transaction> transactionList = transactionRepository.findByAccountId(accountId);
        assertEquals(transactionList.size(),2);


    }
}