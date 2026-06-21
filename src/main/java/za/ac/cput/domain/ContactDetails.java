package za.ac.cput.domain;

import jakarta.persistence.*;
import jakarta.persistence.Embedded;
import za.ac.cput.util.IdGenerator;

import java.util.regex.Pattern;

@Entity
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String cellNumber;
    private String email;
    private String homePhone;
    private String workPhone;
    private String emergencyContact;
    private String emergencyPhone;

    protected ContactDetails() {}

    private ContactDetails(Builder builder) {
        this.contactId = builder.contactId;
        this.cellNumber = builder.cellNumber;
        this.email = builder.email;
        this.homePhone = builder.homePhone;
        this.workPhone = builder.workPhone;
        this.emergencyContact = builder.emergencyContact;
        this.emergencyPhone = builder.emergencyPhone;
    }

    // Getters
    public Long getContactId() { return contactId; }
    public String getCellNumber() { return cellNumber; }
    public String getEmail() { return email; }
    public String getHomePhone() { return homePhone; }
    public String getWorkPhone() { return workPhone; }
    public String getEmergencyContact() { return emergencyContact; }
    public String getEmergencyPhone() { return emergencyPhone; }

    // Business methods
    public boolean validateEmail() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(emailRegex, email);
    }

    public boolean validatePhone() {
        // South African phone number validation
        return cellNumber != null && cellNumber.matches("^\\+27[0-9]{9}$");
    }

    public void sendNotification(String message) {
        // Implementation for sending notifications
        System.out.println("Sending to " + email + ": " + message);
    }

    @Override
    public String toString() {
        return "ContactDetails{" +
                "contactId=" + contactId +
                ", cellNumber='" + cellNumber + '\'' +
                ", email='" + email + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }

    public static class Builder {
        private Long contactId;
        private String cellNumber;
        private String email;
        private String homePhone;
        private String workPhone;
        private String emergencyContact;
        private String emergencyPhone;

        public Builder(String cellNumber, String email) {
            this.contactId = IdGenerator.getInstance().generateId();
            this.cellNumber = cellNumber;
            this.email = email;
        }

        public Builder setHomePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public Builder setWorkPhone(String workPhone) {
            this.workPhone = workPhone;
            return this;
        }

        public Builder setEmergencyContact(String emergencyContact) {
            this.emergencyContact = emergencyContact;
            return this;
        }

        public Builder setEmergencyPhone(String emergencyPhone) {
            this.emergencyPhone = emergencyPhone;
            return this;
        }

        public Builder copy(ContactDetails contactDetails) {
            this.contactId = contactDetails.contactId;
            this.cellNumber = contactDetails.cellNumber;
            this.email = contactDetails.email;
            this.homePhone = contactDetails.homePhone;
            this.workPhone = contactDetails.workPhone;
            this.emergencyContact = contactDetails.emergencyContact;
            this.emergencyPhone = contactDetails.emergencyPhone;
            return this;
        }

        public ContactDetails build() {
            return new ContactDetails(this);
        }
    }
}
