package za.ac.cput.repository;

import za.ac.cput.domain.CreditCardDetails;

import java.util.List;

public interface ICreditCardDetailsRepository extends IRepository<CreditCardDetails, Long> {
    CreditCardDetails findById(Long id);
    List<CreditCardDetails> findByCustomerId(Long customerId);
    CreditCardDetails findDefaultCardByCustomerId(Long customerId);
}