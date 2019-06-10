package com.blueharvest.transactionservice.service;

import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.repository.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    public void createNewTransaction() {

        Transaction transactionJson = Transaction.builder().id(1L).accountId(1000L).amount(1000.0).build();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transactionJson);

        Transaction newTransaction = transactionService.createNewTransaction(transactionJson);
        assertThat(newTransaction.getId()).isSameAs(transactionJson.getId());

    }

    @Test
    public void getAllTransactionsById() {
        final Long accountId = 1000L;

        Transaction transactionOne = Transaction.builder().amount(1000.0).accountId(accountId).build();
        Transaction transactionTwo = Transaction.builder().amount(1000.0).accountId(accountId).build();
        List<Transaction> transactions = Arrays.asList(transactionOne,transactionTwo);
        when(transactionRepository.findByAccountId(any(Long.class))).thenReturn(transactions);

        List<Transaction> transactionList = transactionService.getAllTransactionsById(accountId);
        assertEquals(transactionList.size(), 2);

    }

}