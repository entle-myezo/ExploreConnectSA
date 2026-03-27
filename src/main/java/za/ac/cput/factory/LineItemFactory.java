 package za.ac.cput.factory;

import za.ac.cput.domain.LineItem;
import za.ac.cput.util.Helper;

        public class LineItemFactory {
    // Basic Line Item
    public static LineItem createLineItem(String description, int quantity, double unitPrice) {
        Helper.requireNotEmptyOrNull(description, "Description");
        Helper.requireNotNegative(quantity, "Quantity");
        Helper.requirePositive(unitPrice, "Unit Price");

        return new LineItem.Builder(description, quantity, unitPrice)
                .build();
    }

    // Line Item for multiple items
    public static LineItem createMultiItemLine(String description, int quantity, double unitPrice) {
        return createLineItem(description, quantity, unitPrice);
    }
}