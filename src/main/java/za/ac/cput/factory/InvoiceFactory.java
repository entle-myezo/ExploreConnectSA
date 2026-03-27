package za.ac.cput.factory;

import za.ac.cput.domain.BillingAddress;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Invoice;

import za.ac.cput.domain.LineItem;
import za.ac.cput.util.Helper;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceFactory {


    public static Invoice createInvoice(
            Booking booking,
            BillingAddress billingAddress
    ) {
        // Validation
        Helper.requireNonNull(booking, "Booking");
        Helper.requireNonNull(billingAddress, "Billing Address");

        return new Invoice.Builder(booking)
                .setBillingAddress(billingAddress)
                .build();
    }


    public static Invoice createFullInvoice(
            Booking booking,
            BillingAddress billingAddress,
            List<LineItem> items,
            String paymentTerms
    ) {

        Invoice baseInvoice = createInvoice(booking, billingAddress);

        // Validate items
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Invoice must have at least one line item");
        }


        Helper.requireNotEmptyOrNull(paymentTerms, "Payment Terms");

        return new Invoice.Builder(booking)
                .copy(baseInvoice)
                .setItems(items)
                .setPaymentTerms(paymentTerms)
                .build();
    }


    public static Invoice createInvoiceWithExtraItem(
            Booking booking,
            BillingAddress billingAddress,
            LineItem item
    ) {
        Helper.requireNonNull(item, "Line Item");

        return new Invoice.Builder(booking)
                .setBillingAddress(billingAddress)
                .addItem(item)
                .build();
    }
}