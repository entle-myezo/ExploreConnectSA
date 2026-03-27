package za.ac.cput.repository;

import za.ac.cput.domain.Invoice;
import za.ac.cput.domain.Booking;

import java.util.List;

public interface IInvoiceRepository extends IRepository<Invoice, Long> {
    Invoice findById(Long id);
    List<Invoice> findByBooking(Booking booking);
}