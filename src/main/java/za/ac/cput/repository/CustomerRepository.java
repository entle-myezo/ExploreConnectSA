package za.ac.cput.repository;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerRepository implements ICustomerRepository {
    private static CustomerRepository repo = null;
    private Map<Long, Customer> customerMap;

    private CustomerRepository() {
        customerMap = new HashMap<>();
    }

    public static CustomerRepository getRepository() {
        if (repo == null) {
            repo = new CustomerRepository();
        }
        return repo;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer.getUserId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (customerMap.containsKey(customer.getUserId())) {
            throw new IllegalArgumentException("Customer with ID " + customer.getUserId() + " already exists");
        }
        customerMap.put(customer.getUserId(), customer);
        return customer;
    }

    @Override
    public Customer read(Long id) {
        return customerMap.get(id);
    }

    @Override
    public Customer update(Customer customer) {
        if (customer.getUserId() == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (!customerMap.containsKey(customer.getUserId())) {
            throw new IllegalArgumentException("Customer with ID " + customer.getUserId() + " does not exist");
        }
        customerMap.put(customer.getUserId(), customer);
        return customer;
    }

    @Override
    public Customer delete(Long id) {
        return customerMap.remove(id);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer findById(Long id) {
        return read(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerMap.values().stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Customer findByIdentityNumber(String identityNumber) {
        return customerMap.values().stream()
                .filter(customer -> identityNumber.equals(customer.getIdentityNumber()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerMap.values().stream()
                .filter(customer -> customer.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> findByLoyaltyTier(String tier) {
        return customerMap.values().stream()
                .filter(customer -> customer.getLoyaltyProgram() != null &&
                        customer.getLoyaltyProgram().getTier().equalsIgnoreCase(tier))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerMap.values().stream()
                .anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email));
    }

}
