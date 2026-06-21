package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import java.time.format.DateTimeParseException;


public class Helper {

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    // ==================== PHONE VALIDATION ====================

    /**
     * Validates phone number using Google's libphonenumber library
     * @param phoneNumber The phone number to validate
     * @param regionCode The region code (e.g., "ZA" for South Africa)
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber, String regionCode) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        try {
            Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, regionCode);
            return phoneUtil.isValidNumber(number);
        } catch (NumberParseException e) {
            System.err.println("Phone parsing error: " + e.getErrorType());
            return false;
        }
    }

    /**
     * Validates South African phone number
     * @param phoneNumber The phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidSouthAfricanPhone(String phoneNumber) {
        return isValidPhoneNumber(phoneNumber, "ZA");
    }

    /**
     * Validates phone number and throws exception if invalid
     */
    public static void requireValidPhoneNumber(String phoneNumber, String regionCode, String fieldName) {
        if (!isValidPhoneNumber(phoneNumber, regionCode)) {
            throw new IllegalArgumentException(fieldName + " is invalid. Expected format: +27XXXXXXXXX or 0XXXXXXXXX");
        }
    }

    /**
     * Validates South African phone number and throws exception if invalid
     */
    public static void requireValidSouthAfricanPhone(String phoneNumber, String fieldName) {
        requireValidPhoneNumber(phoneNumber, "ZA", fieldName);
    }

    // ==================== EMAIL VALIDATION ====================

    /**
     * Validates email using regex pattern
     */
    public static boolean isValidEmailWithRegex(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern PATTERN = Pattern.compile(REGEX_EMAIL);
        return PATTERN.matcher(email).matches();
    }

    /**
     * Validates email using Apache Commons validator
     */
    public static boolean isValidEmailWithApacheCommons(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EmailValidator.getInstance().isValid(email);
    }

    /**
     * Validates email and throws exception if invalid
     */
    public static void requireValidEmail(String email, String fieldName) {
        if (!isValidEmailWithApacheCommons(email) && !isValidEmailWithRegex(email)) {
            throw new IllegalArgumentException(fieldName + " is invalid. Expected format: name@domain.com");
        }
    }

    // ==================== STRING VALIDATION ====================

    /**
     * Validates that a string is not null or empty
     */
    public static void requireNotEmptyOrNull(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    /**
     * Validates that a string is not null or empty and returns the trimmed value
     */
    public static String requireNotEmptyOrNullAndTrim(String value, String fieldName) {
        requireNotEmptyOrNull(value, fieldName);
        return value.trim();
    }

    /**
     * Validates that a string contains only letters and spaces
     */
    public static boolean isAlphaWithSpaces(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches("^[A-Za-z\\s]+$");
    }

    /**
     * Validates that a string contains only letters and spaces - throws exception
     */
    public static void requireAlphaWithSpaces(String value, String fieldName) {
        if (!isAlphaWithSpaces(value)) {
            throw new IllegalArgumentException(fieldName + " must contain only letters and spaces");
        }
    }

    /**
     * Validates that a string contains only alphanumeric characters
     */
    public static boolean isAlphanumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.matches("^[A-Za-z0-9]+$");
    }

    /**
     * Validates that a string is a valid student number (CPUT format)
     * Example: 211204803 (9 digits)
     */
    public static boolean isValidStudentNumber(String studentNumber) {
        if (studentNumber == null || studentNumber.isEmpty()) {
            return false;
        }
        return studentNumber.matches("^[0-9]{9}$");
    }

    /**
     * Validates student number and throws exception if invalid
     */
    public static void requireValidStudentNumber(String studentNumber, String fieldName) {
        if (!isValidStudentNumber(studentNumber)) {
            throw new IllegalArgumentException(fieldName + " must be a valid 9-digit CPUT student number");
        }
    }

    // ==================== NUMERIC VALIDATION ====================

    /**
     * Validates that a number is not negative
     */
    public static boolean requireNotNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }

    /**
     * Validates that a number is not negative (double)
     */
    public static boolean requireNotNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return true;
    }

    /**
     * Validates that a number is positive (greater than 0)
     */
    public static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    /**
     * Validates that a number is positive (greater than 0) - int
     */
    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    /**
     * Validates that a value is within a specified range
     */
    public static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
        }
    }

    /**
     * Validates that a double value is within a specified range
     */
    public static void requireInRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max);
        }
    }

    // ==================== DATE VALIDATION ====================

    /**
     * Validates that a date is not null
     */
    public static void requireNonNullDate(LocalDateTime date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    /**
     * Validates that a date is in the future
     */
    public static void requireFutureDate(LocalDateTime date, String fieldName) {
        requireNonNullDate(date, fieldName);
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(fieldName + " must be in the future");
        }
    }

    /**
     * Validates that a date is in the past
     */
    public static void requirePastDate(LocalDateTime date, String fieldName) {
        requireNonNullDate(date, fieldName);
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(fieldName + " must be in the past");
        }
    }

    /**
     * Validates that end date is after start date
     */
    public static void requireDateRange(LocalDateTime start, LocalDateTime end, String startField, String endField) {
        requireNonNullDate(start, startField);
        requireNonNullDate(end, endField);
        if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException(endField + " must be after " + startField);
        }
    }

    /**
     * Validates that a date string is in the correct format
     */
    public static boolean isValidDateFormat(String dateStr, String format) {
        if (dateStr == null || dateStr.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // ==================== ID VALIDATION ====================

    /**
     * Validates South African ID number format (13 digits)
     * Format: YYMMDD G S SS C
     */
    public static boolean isValidSouthAfricanId(String idNumber) {
        if (idNumber == null || idNumber.trim().isEmpty()) {
            return false;
        }
        if (!idNumber.matches("^[0-9]{13}$")) {
            return false;
        }
        try {
            int year = Integer.parseInt(idNumber.substring(0, 2));
            int month = Integer.parseInt(idNumber.substring(2, 4));
            int day = Integer.parseInt(idNumber.substring(4, 6));

            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > getMaxDaysInMonth(month, year)) {
                return false;
            }
            return isValidLuhnChecksum(idNumber);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates South African ID and throws exception if invalid
     */
    public static void requireValidSouthAfricanId(String idNumber, String fieldName) {
        if (!isValidSouthAfricanId(idNumber)) {
            throw new IllegalArgumentException(fieldName + " is invalid. Must be a valid 13-digit South African ID");
        }
    }

    /**
     * Validates passport number format (alphanumeric, 6-9 characters)
     */
    public static boolean isValidPassportNumber(String passportNumber) {
        if (passportNumber == null || passportNumber.trim().isEmpty()) {
            return false;
        }
        return passportNumber.matches("^[A-Z0-9]{6,9}$");
    }

    /**
     * Validates passport number and throws exception if invalid
     */
    public static void requireValidPassportNumber(String passportNumber, String fieldName) {
        if (!isValidPassportNumber(passportNumber)) {
            throw new IllegalArgumentException(fieldName + " is invalid. Must be 6-9 alphanumeric characters");
        }
    }

    /**
     * Validates South African postal code (4 digits)
     */
    public static boolean isValidPostalCode(String postalCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            return false;
        }
        return postalCode.matches("^[0-9]{4}$");
    }

    // ==================== OBJECT VALIDATION ====================

    /**
     * Validates that an object is not null
     */
    public static <T> T requireNonNull(T obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        return obj;
    }

    /**
     * Validates that a list is not null or empty
     */
    public static void requireNotEmpty(List<?> list, String fieldName) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    // ==================== DATE UTILITY METHODS ====================

    /**
     * Gets the maximum days in a month (considering leap years)
     */
    private static int getMaxDaysInMonth(int month, int year) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2) {
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            return isLeapYear ? 29 : 28;
        }
        return daysInMonth[month - 1];
    }

    /**
     * Validates Luhn checksum for ID verification
     */
    private static boolean isValidLuhnChecksum(String idNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = idNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(idNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    // ==================== BUSINESS RULE VALIDATIONS ====================

    /**
     * Validates age (must be >= 18 for adult travelers)
     */
    public static void validateAge(int age, String fieldName) {
        if (age < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        if (age > 120) {
            throw new IllegalArgumentException(fieldName + " cannot be greater than 120");
        }
    }

    /**
     * Validates hotel star rating (1-5)
     */
    public static void validateStarRating(int rating, String fieldName) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(fieldName + " must be between 1 and 5");
        }
    }

    /**
     * Validates room number format
     */
    public static boolean isValidRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            return false;
        }
        return roomNumber.matches("^[A-Z0-9]{2,5}$");
    }

    /**
     * Validates flight number format (e.g., SA123, BA456)
     */
    public static boolean isValidFlightNumber(String flightNumber) {
        if (flightNumber == null || flightNumber.trim().isEmpty()) {
            return false;
        }
        return flightNumber.matches("^[A-Z]{2}[0-9]{1,4}$");
    }

    /**
     * Validates booking reference format
     */
    public static boolean isValidBookingReference(String bookingReference) {
        if (bookingReference == null || bookingReference.trim().isEmpty()) {
            return false;
        }
        return bookingReference.matches("^[A-Z0-9-]{8,12}$");
    }

}








