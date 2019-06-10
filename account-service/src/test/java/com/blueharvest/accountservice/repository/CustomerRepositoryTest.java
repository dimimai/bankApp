package com.blueharvest.accountservice.repository;

import com.blueharvest.accountservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void init(){
        customer =   Customer.builder().firstName("John").lastName("Doe").birthDate(new Date()).build();
    }

    @Test
    public void whenGetByCustomerIdThenReturnCustomer() {

        customer =  customerRepository.save(customer);
        Optional<Customer> newCustomer = customerRepository.findById(customer.getId());
        assertEquals(newCustomer.get(),customer);

    }

    @Test
    public void whenGetByInvalidCustomerIdThenReturnNone() {
        Optional<Customer> newCustomer = customerRepository.findById(1000L);
        assertEquals(newCustomer.isPresent(),false);

    }
}