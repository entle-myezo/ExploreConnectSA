package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Invoice {
    @Id
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<LineItem> items = new ArrayList<>();

    private double tax;
    private double total;
    private String paymentTerms;

    protected Invoice(){}

    private Invoice(Builder builder) {
        this.invoiceNumber = builder.invoiceNumber;
        this.issueDate = builder.issueDate;
        this.dueDate = builder.dueDate;
        this.booking = builder.booking;
        this.items = builder.items != null ? builder.items : new ArrayList<>();
        this.tax = builder.tax;
        this.total = builder.total;
        this.paymentTerms = builder.paymentTerms;

        calculateTotals();
    }

    // Getters
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDateTime getIssueDate() { return issueDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public Booking getBooking() {return booking;}
    public List<LineItem> getItems() { return items; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
    public String getPaymentTerms() { return paymentTerms; }

    // Business methods
    public void calculateTotals() {
        double subtotal = items.stream()
                .mapToDouble(LineItem::getTotal)
                .sum();
        this.tax = subtotal * 0.15; // 15% VAT
        this.total = subtotal + tax;
    }

    public File generatePDF() {
        return new File(invoiceNumber + ".pdf");
    }

    public void sendViaEmail() {
        System.out.println("Invoice " + invoiceNumber + " emailed to " +
                booking.getBookedBy().getEmail());
    }

    public void addItem(LineItem item) {
        this.items.add(item);
        calculateTotals();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", tax=" + tax +
                ", total=" + total +
                '}';
    }

    public static class Builder {
        private String invoiceNumber;
        private LocalDateTime issueDate;
        private LocalDateTime dueDate;
        private Booking booking;
        private List<LineItem> items;
        private double tax;
        private double total;
        private String paymentTerms;


        public Builder setItems(List<LineItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(LineItem item) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return this;
        }

        public Builder setPaymentTerms(String paymentTerms) {
            this.paymentTerms = paymentTerms;
            return this;
        }

        public Builder copy(Invoice invoice) {
            this.invoiceNumber = invoice.invoiceNumber;
            this.issueDate = invoice.issueDate;
            this.dueDate = invoice.dueDate;
            this.booking = invoice.booking;
            this.items = invoice.items;
            this.tax = invoice.tax;
            this.total = invoice.total;
            this.paymentTerms = invoice.paymentTerms;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}