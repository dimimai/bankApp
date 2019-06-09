package com.blueharvest.accountservice.controller;

import com.blueharvest.accountservice.AccountServiceApplication;
import com.blueharvest.accountservice.TransactionServiceProxy;
import com.blueharvest.accountservice.exception.CustomerNotFoundException;
import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.AccountType;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.service.AccountService;
import com.blueharvest.accountservice.service.CustomerService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ AccountServiceApplication.class })
public class AccountControllerTest {


    private MockMvc mockMvc;
    private Gson gson = new Gson();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
     CustomerService customerService;

    @MockBean
     AccountService accountService;

    @MockBean
    TransactionServiceProxy proxy;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createAccountValidRequest() throws Exception {

        Date date = new Date();

        Customer customer = Customer.builder().id(1000L).firstName("John").lastName("Doe").birthDate(date).build();
        Account accountJson = Account.builder().balance(1000.0).accountType(AccountType.current).iban("test")
                .build();
        Account account = Account.builder().id(1L).balance(1000.0).accountType(AccountType.current).iban("test").customer(customer)
                .build();

        given(customerService.getCustomerById(1000L)).willReturn(customer);
        given(accountService.createNewAccoount(any(Account.class))).willReturn(account);

        mockMvc.perform(post("/customers/{customerId}/accounts",1000L)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(gson.toJson(accountJson))
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.accountType").value("current"))
                .andExpect(jsonPath("$.balance").value(1000.0));

    }

    @Test
    public void createAccountNotValidRequest() throws Exception {
        Account accountJson = Account.builder().balance(1000.0).accountType(AccountType.current).iban("test").build();

        given(customerService.getCustomerById(1000L)).willThrow(new CustomerNotFoundException("Does not exist"));
        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/customers/{customerId}/accounts",1000L)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(accountJson)))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString().contains("Does not exist")).isEqualTo(true);

    }

}