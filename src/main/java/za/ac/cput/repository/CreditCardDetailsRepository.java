package za.ac.cput.repository;

import za.ac.cput.domain.CreditCardDetails;
import za.ac.cput.util.Helper;

import java.util.ArrayList;
import java.util.List;

public class CreditCardDetailsRepository {

    private final List<CreditCardDetails> storage = new ArrayList<>();

    public CreditCardDetails create(CreditCardDetails card) {
        Helper.requireNonNull(card, "CreditCardDetails");
        storage.add(card);
        return card;
    }


    public CreditCardDetails update(CreditCardDetails card) {
        Helper.requireNonNull(card, "CreditCardDetails");
        int index = storage.indexOf(card);
        if (index != -1) {
            storage.set(index, card);
            return card;
        }
        return null;
    }

    public boolean delete(CreditCardDetails card) {
        Helper.requireNonNull(card, "CreditCardDetails");
        return storage.remove(card);
    }


    public List<CreditCardDetails> getAll() {
        return new ArrayList<>(storage);
    }


    public List<CreditCardDetails> findDefaultCards() {
        List<CreditCardDetails> result = new ArrayList<>();
        for (CreditCardDetails c : storage) {
            if (c.isDefault()) {
                result.add(c);
            }
        }
        return result;
    }


}