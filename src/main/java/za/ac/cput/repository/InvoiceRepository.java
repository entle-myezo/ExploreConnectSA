package za.ac.cput.repository;

import za.ac.cput.domain.Invoice;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.IInvoiceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceRepository implements IInvoiceRepository {

    private final List<Invoice> storage = new ArrayList<>();


    @Override
    public Invoice create(Invoice invoice) {
        Objects.requireNonNull(invoice, "Invoice cannot be null");
        storage.add(invoice);
        return invoice;
    }


    @Override
    public Invoice read(Long id) {
        Objects.requireNonNull(id, "ID cannot be null");
        for (Invoice i : storage) {
            if (i.getInvoiceNumber() != null && i.getInvoiceNumber().equals(id.toString())) {
                return i;
            }
        }
        return null;
    }


    @Override
    public Invoice update(Invoice invoice) {
        Objects.requireNonNull(invoice, "Invoice cannot be null");
        for (int index = 0; index < storage.size(); index++) {
            if (storage.get(index).getInvoiceNumber().equals(invoice.getInvoiceNumber())) {
                storage.set(index, invoice);
                return invoice;
            }
        }
        return null;
    }


    @Override
    public Invoice delete(Long id) {
        Objects.requireNonNull(id, "ID cannot be null");
        Invoice toDelete = read(id);
        if (toDelete != null) {
            storage.remove(toDelete);
            return toDelete;
        }
        return null;
    }

    @Override
    public Invoice findById(Long id) {
        return null;
    }


    @Override
    public List<Invoice> findByBooking(Booking booking) {
        Objects.requireNonNull(booking, "Booking cannot be null");
        List<Invoice> result = new ArrayList<>();
        for (Invoice i : storage) {
            if (booking.equals(i.getBooking())) {
                result.add(i);
            }
        }
        return result;
    }


    @Override
    public List<Invoice> getAll() {
        return new ArrayList<>(storage);
    }


}