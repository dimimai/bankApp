package com.blueharvest.transactionservice.controller;

import com.blueharvest.transactionservice.TransactionServiceApplication;
import com.blueharvest.transactionservice.model.Transaction;
import com.blueharvest.transactionservice.service.TransactionService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TransactionServiceApplication.class})
public class TransactionControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Gson gson = new Gson();

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void whenValidRequestThenCreateNewTransaction() throws Exception {
        Transaction transactionJson = Transaction.builder().accountId(1000L).amount(1000.0).build();
        Transaction transaction = Transaction.builder().id(1L).accountId(1000L).amount(1000.0).build();

        given(transactionService.createNewTransaction(any(Transaction.class))).willReturn(transaction);

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(transactionJson))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.amount").value(1000.0))
                .andExpect(jsonPath("$.accountId").value("1000"));
    }

    @Test
    public void whenValidRequestThenReturnTransactions() throws Exception {
        Transaction transactionOne = Transaction.builder().id(1L).amount(1000.0).accountId(100L).build();
        Transaction transactionTwo = Transaction.builder().id(2L).amount(1000.0).accountId(100L).build();

        List<Transaction> transactions = Arrays.asList(transactionOne, transactionTwo);

        given(transactionService.getAllTransactionsById(100L)).willReturn(transactions);

        mockMvc.perform(get("/transactions/{accountId}", 100L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));

    }
}