package za.ac.cput.repository;

import za.ac.cput.domain.LineItem;
import java.util.*;
import za.ac.cput.util.Helper;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LineItemTest {

    private LineItemRepository repo;

    @BeforeEach
    void setUp() {
        repo = LineItemRepository.getRepository();
    }

    @Test
    void testCreateLineItem() {
        LineItem item = LineItemFactory.createLineItem("Laptop", 2, 1500.0);
        assertNotNull(item);
        assertEquals("Laptop", item.getDescription());
        assertEquals(2, item.getQuantity());
        assertEquals(1500.0, item.getUnitPrice());
    }

    @Test
    void testRepositoryCreateAndRead() {
        LineItem item = LineItemFactory.createLineItem("Mouse", 5, 25.0);
        repo.create(item);
        LineItem fetched = repo.getAll().get(0);
        assertEquals("Mouse", fetched.getDescription());
    }

    @Test
    void testFindByDescription() {
        LineItem item = LineItemFactory.createLineItem("Keyboard", 3, 45.0);
        repo.create(item);
        assertFalse(repo.findByDescription("Keyboard").isEmpty());
    }
}