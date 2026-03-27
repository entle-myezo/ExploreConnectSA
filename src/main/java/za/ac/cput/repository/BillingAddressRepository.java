package za.ac.cput.repository;

import za.ac.cput.util.Helper;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BillingAddressRepository {

    public static class billingAddressRepository implements IBillingAddressRepository {
        private static billingAddressRepository repo = null;
        private Map<Long, BillingAddress> addressMap;
        private AtomicLong idCounter;

        private billingAddressRepository() {
            addressMap = new HashMap<>();
            idCounter = new AtomicLong(1);
        }

        public static billingAddressRepository getRepository() {
            if (repo == null) {
                repo = new billingAddressRepository();
            }
            return repo;
        }

        @Override
        public za.ac.cput.repository.BillingAddressRepository.BillingAddressRepository create(BillingAddress address) {
            Helper.requireNonNull(address, "Billing Address");
            Long id = idCounter.getAndIncrement();
            addressMap.put(id, address);
            return billingAddress;
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
}
