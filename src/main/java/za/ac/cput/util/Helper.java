package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class Helper {
    private Helper() {
        throw new AssertionError("ValidationHelper cannot be instantiated");
    }
    public static void requireNotEmptyOrNull(String value, String fieldName) {
        if(value==null || value.isEmpty()){
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }
    public static <T> T requireNonNull(T obj, String fieldName) {
        return requireNonNull(obj, fieldName);
    }
    public static boolean requireNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }
    public static boolean isValidEmail(String email) {
        return isValidEmailWithRegex(email);
    }

    public static boolean isValidEmailWithRegex(String email) {
        String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern PATTERN = Pattern.compile(REGEX_EMAIL);
        requireNotEmptyOrNull(email, "Email");
        if (!PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid Email" + email);
        }
        return true;
    }

    public static boolean isValidEmailWithApacheCommons(String email){
        requireNotEmptyOrNull(email, "Email");

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid Email format: " + email);
        }
        return true;
    }
    public static void validateDateRange(LocalDateTime start, LocalDateTime end, String startField, String endField) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Dates cannot be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException(startField + " must be before " + endField);
        }
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime != null ? dateTime.format(formatter) : null;
    }
    /**
      * Validates South African phone number
     */
    public static boolean isValidSouthAfricanPhone(String phone) {
        requireNotEmptyOrNull(phone, "Phone number");

        // Format: +27XXXXXXXXX or 0XXXXXXXXX
        String phoneRegex = "^(\\+27|0)[0-9]{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);

        if (!pattern.matcher(phone).matches()) {
            throw new IllegalArgumentException("Invalid South African phone number: " + phone);
        }
        return true;
    }

    /**
     * Validates South African ID number
     */
    public static boolean isValidSouthAfricanId(String idNumber) {
        return false;
    }

    public static void requireNotEmptyOrNull(List<String> rewards, String rewards1) {
    }

    public static void requirePositive(double securityDeposit, String securityDeposit1) {
    }
}


