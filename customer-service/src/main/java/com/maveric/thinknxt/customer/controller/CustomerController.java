package com.maveric.thinknxt.customer.controller;

import com.maveric.thinknxt.customer.entity.Customer;
import com.maveric.thinknxt.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("welcome test!");
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        LOGGER.info("All Customers");
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        LOGGER.info("Customer findBy: {}", id);
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        LOGGER.info("Customer add: {}", customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.addCustomer(customer));
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        LOGGER.info("Customer deleteBy: {}", id);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully!");
    }
}
