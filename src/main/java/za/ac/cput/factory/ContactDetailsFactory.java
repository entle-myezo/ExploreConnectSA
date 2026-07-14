package za.ac.cput.factory;
/* ContactDetailsFactory.java

   ContactDetails Factory class

   Author: Zamandlovu C Ndlovu (211204803)

   Date: 28 June 2026
*/
import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

public class ContactDetailsFactory {

    private static final IdGenerator idGenerator = new IdGenerator();

    //Creates basic contact details

    public static ContactDetails createContactDetails(String cellNumber, String email) {
        Helper.requireValidSouthAfricanPhone(cellNumber, "Cell Number");
        Helper.requireValidEmail(email, "Email");

        Long contactId = idGenerator.generateLongId();

        return new ContactDetails.Builder()
                .setContactId(contactId)
                .setCellNumber(cellNumber)
                .setEmail(email)
                .build();
    }

    // Creates full contact details with optional fields

    public static ContactDetails createFullContactDetails(String cellNumber, String email,
                                                          String homePhone, String workPhone,
                                                          String emergencyContact, String emergencyPhone) {
        ContactDetails contact = createContactDetails(cellNumber, email);

        return new ContactDetails.Builder()
                .copy(contact)
                .setHomePhone(homePhone)
                .setWorkPhone(workPhone)
                .setEmergencyContact(emergencyContact)
                .setEmergencyPhone(emergencyPhone)
                .build();
    }
}