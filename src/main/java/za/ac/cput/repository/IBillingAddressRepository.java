package za.ac.cput.repository;

import za.ac.cput.domain.BillingAddress;

import java.util.List;

public interface IBillingAddressRepository extends IRepository<BillingAddress, Long> {
    List<BillingAddress> findByCity(String city);
    List<BillingAddress> findByPostalCode(String postalCode);
    List<BillingAddress> findByCountry(String country);
}