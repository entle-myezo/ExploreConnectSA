package za.ac.cput.repository;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class LineItemRepository implements ILineItemRepository {

    private static LineItemRepository repo = null;
    private Map<Long, LineItem> lineItemMap;
    private AtomicLong idCounter;

    private LineItemRepository() {
        lineItemMap = new HashMap<>();
        idCounter = new AtomicLong(1);
    }

    public static LineItemRepository getRepository() {
        if (repo == null) {
            repo = new LineItemRepository();
        }
        return repo;
    }

    @Override
    public LineItem create(LineItem lineItem) {
        Helper.requireNonNull(lineItem, "Line Item");
        Long id = idCounter.getAndIncrement();
        lineItemMap.put(id, lineItem);
        return lineItem;
    }

    @Override
    public LineItem read(Long id) {
        Helper.requireNonNull(id, "Line Item ID");
        return lineItemMap.get(id);
    }

    @Override
    public LineItem update(LineItem lineItem) {
        Helper.requireNonNull(lineItem, "Line Item");
        return lineItem; // consider updating in map if needed
    }

    @Override
    public LineItem delete(Long id) {
        Helper.requireNonNull(id, "Line Item ID");
        return lineItemMap.remove(id);
    }

    @Override
    public List<LineItem> getAll() {
        return new ArrayList<>(lineItemMap.values());
    }

    @Override
    public LineItem findById(Long id) {
        return read(id);
    }

    @Override
    public List<LineItem> findByInvoiceId(String invoiceId) {
        Helper.requireNotEmptyOrNull(invoiceId, "Invoice ID");
        return lineItemMap.values().stream()
                .filter(item -> false) // FIXME: needs implementation
                .collect(Collectors.toList());
    }

    @Override
    public List<LineItem> findByDescription(String description) {
        Helper.requireNotEmptyOrNull(description, "Description");
        return lineItemMap.values().stream()
                .filter(item -> item.getDescription() != null &&
                        item.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());
    }
}