package za.ac.cput.repository;

import org.junit.jupiter.api.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.IdentityType;
import za.ac.cput.domain.LanguageType;
import za.ac.cput.domain.LoyaltyProgram;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.LoyaltyProgramFactory;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerRepositoryTest {

    private static CustomerRepository repository;
    private static Customer customer;
    private static LoyaltyProgram loyaltyProgram;
    private static Long customerId;

    @BeforeAll
    static void setUp() {
        repository = CustomerRepository.getRepository();

        // Create Loyalty Program with 500 points
        loyaltyProgram = LoyaltyProgramFactory.createLoyaltyProgramWithPoints(500);

        // Create Customer with provided data
        customer = CustomerFactory.createCustomerWithIdentity(
                "Zama",                          // firstName
                "Ndlovu",                        // lastName
                "zamandlovu@gmail.com",          // email
                "password123",                   // password
                IdentityType.RSA_ID,             // identityType
                "9108191150087",                 // identityNumber (RSA ID)
                LocalDateTime.of(1991, 8, 19, 0, 0),  // dateOfBirth
                "South African"                  // nationality
        );

        // Add loyalty program and language
        Customer fullCustomer = new Customer.Builder(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                "password123")
                .setPreferredLanguage(LanguageType.ENGLISH)
                .setLoyaltyProgram(loyaltyProgram)
                .copy(customer)
                .build();

        customer = fullCustomer;
        customerId = customer.getUserId();
    }

    @Test
    @Order(1)
    @DisplayName("Should create customer in repository")
    void createCustomer() {
        Customer created = repository.create(customer);

        assertNotNull(created);
        assertNotNull(created.getUserId());
        assertEquals("Zama", created.getFirstName());
        assertEquals("Ndlovu", created.getLastName());
        assertEquals("zamandlovu@gmail.com", created.getEmail());
        assertEquals(IdentityType.RSA_ID, created.getIdentityType());
        assertEquals("9108191150087", created.getIdentityNumber());
        assertEquals(LocalDateTime.of(1991, 8, 19, 0, 0), created.getDateOfBirth());
        assertEquals("South African", created.getNationality());
        assertEquals(LanguageType.ENGLISH, created.getPreferredLanguage());
        assertNotNull(created.getLoyaltyProgram());
        assertEquals(500, created.getLoyaltyProgram().getPoints());

        System.out.println("=== Customer Created ===");
        System.out.println("ID: " + created.getUserId());
        System.out.println("Name: " + created.getFullName());
        System.out.println("Email: " + created.getEmail());
        System.out.println("RSA ID: " + created.getIdentityNumber());
        System.out.println("Loyalty Points: " + created.getLoyaltyProgram().getPoints());
    }

    @Test
    @Order(2)
    @DisplayName("Should read customer by ID")
    void readCustomer() {
        Customer found = repository.read(customerId);

        assertNotNull(found);
        assertEquals(customerId, found.getUserId());
        assertEquals("Zama", found.getFirstName());
        assertEquals("Ndlovu", found.getLastName());

        System.out.println("=== Customer Read ===");
        System.out.println("Found: " + found.getFullName());
        System.out.println("ID: " + found.getUserId());
    }

    @Test
    @Order(3)
    @DisplayName("Should find customer by email")
    void findByEmail() {
        Customer found = repository.findByEmail("zamandlovu@gmail.com");

        assertNotNull(found);
        assertEquals(customerId, found.getUserId());
        assertEquals("zamandlovu@gmail.com", found.getEmail());

        System.out.println("=== Find by Email ===");
        System.out.println("Email: " + found.getEmail());
        System.out.println("Customer: " + found.getFullName());
    }

    @Test
    @Order(4)
    @DisplayName("Should find customer by identity number")
    void findByIdentityNumber() {
        Customer found = repository.findByIdentityNumber("9108191150087");

        assertNotNull(found);
        assertEquals("9108191150087", found.getIdentityNumber());

        System.out.println("=== Find by Identity Number ===");
        System.out.println("ID Number: " + found.getIdentityNumber());
        System.out.println("Customer: " + found.getFullName());
    }

    @Test
    @Order(5)
    @DisplayName("Should find customers by last name")
    void findByLastName() {
        List<Customer> customers = repository.findByLastName("Ndlovu");

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
        assertTrue(customers.stream().allMatch(c -> c.getLastName().equals("Ndlovu")));

        System.out.println("=== Find by Last Name ===");
        System.out.println("Last Name: Ndlovu");
        System.out.println("Found: " + customers.size() + " customer(s)");
    }

    @Test
    @Order(6)
    @DisplayName("Should find customers by loyalty tier")
    void findByLoyaltyTier() {
        List<Customer> customers = repository.findByLoyaltyTier("BRONZE");

        assertNotNull(customers);
        // With 500 points, tier should be BRONZE
        assertTrue(customers.stream().allMatch(c ->
                c.getLoyaltyProgram() != null &&
                        c.getLoyaltyProgram().getTier().equals("BRONZE")));

        System.out.println("=== Find by Loyalty Tier ===");
        System.out.println("Tier: BRONZE");
        System.out.println("Found: " + customers.size() + " customer(s)");
    }

    @Test
    @Order(7)
    @DisplayName("Should check if email exists")
    void existsByEmail() {
        boolean exists = repository.existsByEmail("zamandlovu@gmail.com");
        assertTrue(exists);

        boolean notExists = repository.existsByEmail("nonexistent@email.com");
        assertFalse(notExists);

        System.out.println("=== Email Exists Check ===");
        System.out.println("zamandlovu@gmail.com: " + (exists ? "Exists ✓" : "Not Found"));
        System.out.println("nonexistent@email.com: " + (notExists ? "Exists" : "Not Found ✓"));
    }

    @Test
    @Order(8)
    @DisplayName("Should update customer")
    void updateCustomer() {
        // Update customer
        Customer updatedCustomer = new Customer.Builder(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                "newPassword123")
                .setLoyaltyPoints(1000)
                .setPreferredLanguage(LanguageType.ZULU)
                .copy(customer)
                .build();

        Customer result = repository.update(updatedCustomer);

        assertNotNull(result);
        assertEquals(1000, result.getLoyaltyPoints());
        assertEquals(LanguageType.ZULU, result.getPreferredLanguage());

        System.out.println("=== Customer Updated ===");
        System.out.println("Updated Points: " + result.getLoyaltyPoints());
        System.out.println("Updated Language: " + result.getPreferredLanguage());
    }

    @Test
    @Order(9)
    @DisplayName("Should get all customers")
    void getAllCustomers() {
        List<Customer> allCustomers = repository.getAll();

        assertNotNull(allCustomers);
        assertFalse(allCustomers.isEmpty());

        System.out.println("=== All Customers ===");
        System.out.println("Total Customers: " + allCustomers.size());
        allCustomers.forEach(c -> System.out.println("  - " + c.getFullName() + " (" + c.getEmail() + ")"));
    }

    @Test
    @Order(10)
    @DisplayName("Should delete customer")
    void deleteCustomer() {
        Customer deleted = repository.delete(customerId);

        assertNotNull(deleted);
        assertEquals(customerId, deleted.getUserId());

        Customer notFound = repository.read(customerId);
        assertNull(notFound);

        System.out.println("=== Customer Deleted ===");
        System.out.println("Deleted: " + deleted.getFullName());
        System.out.println("ID: " + deleted.getUserId());
    }
}