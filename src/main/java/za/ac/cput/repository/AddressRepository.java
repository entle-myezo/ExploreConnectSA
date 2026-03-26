package za.ac.cput.repository;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class AddressRepository implements IAddressRepository {
    private static AddressRepository repo = null;
    private Map<Long, Address> addressMap;

    private AddressRepository() {
        addressMap = new HashMap<>();
    }

    public static AddressRepository getRepository() {
        if (repo == null) {
            repo = new AddressRepository();
        }
        return repo;
    }

    @Override
    public Address create(Address address) {
        if (address.getAddressId() == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }
        if (addressMap.containsKey(address.getAddressId())) {
            throw new IllegalArgumentException("Address with ID " + address.getAddressId() + " already exists");
        }
        addressMap.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public Address read(Long id) {
        return addressMap.get(id);
    }

    @Override
    public Address update(Address address) {
        if (address.getAddressId() == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }
        if (!addressMap.containsKey(address.getAddressId())) {
            throw new IllegalArgumentException("Address with ID " + address.getAddressId() + " does not exist");
        }
        addressMap.put(address.getAddressId(), address);
        return address;
    }

    @Override
    public Address delete(Long id) {
        return addressMap.remove(id);
    }

    @Override
    public List<Address> getAll() {
        return new ArrayList<>(addressMap.values());
    }

    @Override
    public Address findById(Long id) {
        return read(id);
    }

    @Override
    public List<Address> findByCustomerId(Long customerId) {
        // This requires a mapping between Address and Customer
        return addressMap.values().stream()
                .filter(address -> false) // Placeholder - needs proper relationship
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressMap.values().stream()
                .filter(address -> address.getCity() != null &&
                        address.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findByPostalCode(String postalCode) {
        return addressMap.values().stream()
                .filter(address -> address.getPostalCode() != null &&
                        address.getPostalCode().equals(postalCode))
                .collect(Collectors.toList());
    }

    @Override
    public Address findDefaultAddressByCustomerId(Long customerId) {
        return addressMap.values().stream()
                .filter(Address::isDefault)
                .findFirst()
                .orElse(null);
    }
}
