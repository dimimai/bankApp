package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    @Test
    public void whenCustomerIdThenReturnCustomer() {
        Customer customer =   Customer.builder().id(1L).firstName("John").lastName("Doe").birthDate(new Date()).build();

        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));

        Customer newCustomer = customerService.getCustomerById(customer.getId());
        assertThat(newCustomer.getId()).isSameAs(customer.getId());

    }

    @Test
    public void whenCustomerIdWrongThenReturnNoCustomer() {
        Customer customer =   Customer.builder().id(1L).firstName("John").lastName("Doe").birthDate(new Date()).build();

        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(new Customer()));

        Customer newCustomer = customerService.getCustomerById(1000L);
        assertThat(newCustomer.getId()).isSameAs(null);

    }
}