package za.ac.cput.repository;

import za.ac.cput.domain.ContactDetails;
import za.ac.cput.util.Helper;

import java.util.*;

public class ContactDetailsRepository  implements IContactDetailsRepository {
    private static ContactDetailsRepository repo = null;
    private Map<Long, ContactDetails> contactMap;

    private ContactDetailsRepository() {
        contactMap = new HashMap<>();
    }

    public static ContactDetailsRepository getRepository() {
        if (repo == null) {
            repo = new ContactDetailsRepository();
        }
        return repo;
    }

    @Override
    public ContactDetails create(ContactDetails contact) {
        if (contact.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }
        if (contactMap.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact with ID " + contact.getContactId() + " already exists");
        }
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public ContactDetails read(Long id) {
        return contactMap.get(id);
    }

    @Override
    public ContactDetails update(ContactDetails contact) {
        if (contact.getContactId() == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }
        if (!contactMap.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact with ID " + contact.getContactId() + " does not exist");
        }
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public ContactDetails delete(Long id) {
        return contactMap.remove(id);
    }

    @Override
    public List<ContactDetails> getAll() {
        return new ArrayList<>(contactMap.values());
    }

    @Override
    public ContactDetails findById(Long id) {
        return read(id);
    }

    @Override
    public ContactDetails findByEmail(String email) {
        return contactMap.values().stream()
                .filter(contact -> contact.getEmail() != null &&
                        contact.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ContactDetails findByCellNumber(String cellNumber) {
        return contactMap.values().stream()
                .filter(contact -> contact.getCellNumber() != null &&
                        contact.getCellNumber().equals(cellNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ContactDetails findByCustomerId(Long customerId) {
        Helper.requireNonNull(customerId, "Customer ID");
        return contactMap.values().stream()
                .findFirst()
                .orElse(null);
    }
}
