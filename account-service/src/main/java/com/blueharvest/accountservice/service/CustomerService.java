package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.exception.CustomerNotFoundException;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long customerId){
        Optional<Customer> customer =  customerRepository.findById(customerId);
        if (!customer.isPresent()){
            throw new CustomerNotFoundException("Customer does not exist");
        }
        return customer.get();

    }

}
