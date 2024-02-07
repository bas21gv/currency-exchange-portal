package com.maveric.thinknxt.customer.service;

import com.maveric.thinknxt.customer.dao.CustomerRepository;
import com.maveric.thinknxt.customer.entity.Customer;
import com.maveric.thinknxt.customer.exception.CustomerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(()-> new CustomerException(String.format("Customer ID : %d exists",id)));
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
