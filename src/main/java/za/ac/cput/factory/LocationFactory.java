 package za.ac.cput.factory;

import za.ac.cput.domain.Location;
import za.ac.cput.util.Helper;
import java.time.LocalDateTime;


 public class LocationFactory {

     // Basic Location with coordinates
     public static Location createLocation(double latitude, double longitude) {
         if (latitude < -90 || latitude > 90) {
             throw new IllegalArgumentException("Latitude must be between -90 and 90");
         }
         if (longitude < -180 || longitude > 180) {
             throw new IllegalArgumentException("Longitude must be between -180 and 180");
         }
         return new Location.Builder(latitude, longitude)
                 .build();
     }

     // Location with address
     public static Location createLocationWithAddress(double latitude, double longitude, String address) {
         Location location = createLocation(latitude, longitude);
         Helper.requireNotEmptyOrNull(address, "Address");
         return new Location.Builder(latitude, longitude)
                 .setAddress(address)
                 .copy(location)
                 .build();
     }

     // Cape Town International Airport
     public static Location createCapeTownAirport() {
         return createLocationWithAddress(-33.9694, 18.5977, "Cape Town International Airport, Matroosfontein, Cape Town, 7490");
     }

     // OR Tambo International Airport
     public static Location createORTamboAirport() {
         return createLocationWithAddress(-26.1392, 28.2460, "O.R. Tambo International Airport, Kempton Park, Johannesburg, 1627");
     }

     // King Shaka International Airport
     public static Location createKingShakaAirport() {
         return createLocationWithAddress(-29.6145, 31.1162, "King Shaka International Airport, La Mercy, Durban, 4405");
     }
 }