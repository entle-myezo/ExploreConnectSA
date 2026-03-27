package za.ac.cput.repository;

import za.ac.cput.domain.PaymentDetails;
import za.ac.cput.domain.PaymentMethod;

import java.util.List;

public interface IPaymentDetailsRepository extends IRepository<PaymentDetails, Long> {
    PaymentDetails findById(Long id);

    // Delete a PaymentDetails
    PaymentDetails delete(PaymentDetails paymentDetails);

    List<PaymentDetails> findByPaymentMethod(PaymentMethod method);
}