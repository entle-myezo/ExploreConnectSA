package za.ac.cput.repository;

import java.util.List;

public interface ILineItemRepository extends IRepository<LineItem, Long> {
    List<LineItem> findByInvoiceId(String invoiceId);
    List<LineItem> findByDescription(String description);
}
