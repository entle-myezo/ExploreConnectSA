package za.ac.cput.repository;

import za.ac.cput.domain.LineItem;

import java.util.List;

public interface ILineItemRepository extends IRepository<LineItem, Long> {
    LineItem findById(Long id);

    List<LineItem> findByInvoiceId(String invoiceId);
    List<LineItem> findByDescription(String description);
}
