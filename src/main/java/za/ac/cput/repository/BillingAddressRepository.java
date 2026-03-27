package za.ac.cput.repository;

import za.ac.cput.domain.BillingAddress;
import za.ac.cput.util.Helper;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class BillingAddressRepository implements IBillingAddressRepository {
        private static BillingAddressRepository repo = null;
        private Map<Long, BillingAddress> addressMap;
        private AtomicLong idCounter;

        private BillingAddressRepository() {
            addressMap = new HashMap<>();
            idCounter = new AtomicLong(1);
        }

        public static BillingAddressRepository getRepository() {
            if (repo == null) {
                repo = new BillingAddressRepository();
            }
            return repo;
        }

        @Override
        public BillingAddress create(BillingAddress address) {
            Helper.requireNonNull(address, "Billing Address");
            Long id = idCounter.getAndIncrement();
            addressMap.put(id, address);
            return address;
        }

        @Override
        public BillingAddress read(Long id) {
            Helper.requireNonNull(id, "Address ID");
            return addressMap.get(id);
        }

        @Override
        public BillingAddress update(BillingAddress address) {
            Helper.requireNonNull(address, "Billing Address");
            return address;
        }

        @Override
        public BillingAddress delete(Long id) {
            Helper.requireNonNull(id, "Address ID");
            return addressMap.remove(id);
        }

        @Override
        public List<BillingAddress> getAll() {
            return new ArrayList<>(addressMap.values());
        }

        @Override
        public BillingAddress findById(Long id) {
            return read(id);
        }

        @Override
        public List<BillingAddress> findByCity(String city) {
            Helper.requireNotEmptyOrNull(city, "City");
            return addressMap.values().stream()
                    .filter(address -> address.getCity() != null &&
                            address.getCity().equalsIgnoreCase(city))
                    .collect(Collectors.toList());
        }

        @Override
        public List<BillingAddress> findByPostalCode(String postalCode) {
            Helper.requireNotEmptyOrNull(postalCode, "Postal Code");
            return addressMap.values().stream()
                    .filter(address -> address.getPostalCode() != null &&
                            address.getPostalCode().equals(postalCode))
                    .collect(Collectors.toList());
        }

        @Override
        public List<BillingAddress> findByCountry(String country) {
            Helper.requireNotEmptyOrNull(country, "Country");
            return addressMap.values().stream()
                    .filter(address -> address.getCountry() != null &&
                            address.getCountry().equalsIgnoreCase(country))
                    .collect(Collectors.toList());
        }
    }

