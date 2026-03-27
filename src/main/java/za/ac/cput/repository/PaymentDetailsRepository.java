package za.ac.cput.repository;

import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.domain.PaymentMethod;
import za.ac.cput.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetailsRepository implements IPaymentDetailsRepository {

    private final List<PaymentDetails> storage = new ArrayList<>();

    @Override
    public PaymentDetails create(PaymentDetails paymentDetails) {
        Helper.requireNonNull(paymentDetails, "PaymentDetails");
        storage.add(paymentDetails);
        return paymentDetails;
    }

    @Override
    public PaymentDetails read(Long aLong) {
        return null;
    }

    // Update a PaymentDetails (replace the old object)
    @Override
    public PaymentDetails update(PaymentDetails paymentDetails) {
        Helper.requireNonNull(paymentDetails, "PaymentDetails");
        int index = storage.indexOf(paymentDetails);
        if (index != -1) {
            storage.set(index, paymentDetails);
            return paymentDetails;
        }
        return null;
    }

    @Override
    public PaymentDetails delete(Long aLong) {
        return null;
    }


    @Override
    public PaymentDetails delete(PaymentDetails paymentDetails) {
        return  null;
    }

    // Get all PaymentDetails
    @Override
    public List<PaymentDetails> getAll() {
        return new ArrayList<>(storage);
    }


    @Override
    public List<PaymentDetails> findByPaymentMethod(PaymentMethod method) {
        Helper.requireNonNull(method, "Payment Method");
        List<PaymentDetails> result = new ArrayList<>();
        for (PaymentDetails p : storage) {
            if (p.getMethod() == method) {
                result.add(p);
            }
        }
        return result;
    }


    @Override
    public PaymentDetails findById(Long id) {
        return null;
    }
}