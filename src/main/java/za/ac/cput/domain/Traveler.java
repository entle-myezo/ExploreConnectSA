package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDate;
import java.util.*;

@Entity
public class Traveler {
    @Id
    private Long travelerId;
    private int adultCount;
    private int childCount;
    private int infantCount;
    private List<String> travelerNames;
    private List<LocalDate> travelerAges;
    private List<String> passportNumbers;

    protected Traveler(){}

    private Traveler(Builder builder) {
        this.travelerId = builder.travelerId;
        this.adultCount = builder.adultCount;
        this.childCount = builder.childCount;
        this.infantCount = builder.infantCount;
        this.travelerNames = builder.travelerNames != null ? builder.travelerNames : new ArrayList<>();
        this.travelerAges = builder.travelerAges != null ? builder.travelerAges : new ArrayList<>();
        this.passportNumbers = builder.passportNumbers != null ? builder.passportNumbers : new ArrayList<>();
    }

    // Getters
    public Long getTravelerId() { return travelerId; }
    public int getAdultCount() { return adultCount; }
    public int getChildCount() { return childCount; }
    public int getInfantCount() { return infantCount; }
    public List<String> getTravelerNames() { return travelerNames; }
    public List<LocalDate> getTravelerAges() { return travelerAges; }
    public List<String> getPassportNumbers() { return passportNumbers; }

    // Business methods
    public void addAdult() {
        this.adultCount++;
    }

    public void removeAdult() {
        if (this.adultCount > 1) {
            this.adultCount--;
        }
    }

    public void addChild() {
        this.childCount++;
    }

    public void removeChild() {
        if (this.childCount > 0) {
            this.childCount--;
        }
    }

    public void addInfant() {
        if (this.infantCount < this.adultCount) {
            this.infantCount++;
        }
    }

    public void removeInfant() {
        if (this.infantCount > 0) {
            this.infantCount--;
        }
    }

    public boolean validateTravelers() {
        return travelerNames.size() == getTotalTravelers();
    }

    public int getTotalTravelers() {
        return adultCount + childCount + infantCount;
    }

    @Override
    public String toString() {
        return "Traveler{" +
                "travelerId=" + travelerId +
                ", adultCount=" + adultCount +
                ", childCount=" + childCount +
                ", infantCount=" + infantCount +
                ", travelerNames=" + travelerNames +
                '}';
    }

    public static class Builder {
        private Long travelerId;
        private int adultCount;
        private int childCount;
        private int infantCount;
        private List<String> travelerNames;
        private List<LocalDate> travelerAges;
        private List<String> passportNumbers;

        public Builder setAdultCount(int adultCount) {
            this.adultCount = adultCount;
            return this;
        }

        public Builder setChildCount(int childCount) {
            this.childCount = childCount;
            return this;
        }

        public Builder setInfantCount(int infantCount) {
            this.infantCount = infantCount;
            return this;
        }

        public Builder setTravelerNames(List<String> travelerNames) {
            this.travelerNames = travelerNames;
            return this;
        }

        public Builder addTravelerName(String name) {
            if (this.travelerNames == null) {
                this.travelerNames = new ArrayList<>();
            }
            this.travelerNames.add(name);
            return this;
        }

        public Builder setTravelerAges(List<LocalDate> travelerAges) {
            this.travelerAges = travelerAges;
            return this;
        }

        public Builder addTravelerAge(LocalDate age) {
            if (this.travelerAges == null) {
                this.travelerAges = new ArrayList<>();
            }
            this.travelerAges.add(age);
            return this;
        }

        public Builder setPassportNumbers(List<String> passportNumbers) {
            this.passportNumbers = passportNumbers;
            return this;
        }

        public Builder addPassportNumber(String passportNumber) {
            if (this.passportNumbers == null) {
                this.passportNumbers = new ArrayList<>();
            }
            this.passportNumbers.add(passportNumber);
            return this;
        }

        public Builder copy(Traveler traveler) {
            this.travelerId = traveler.travelerId;
            this.adultCount = traveler.adultCount;
            this.childCount = traveler.childCount;
            this.infantCount = traveler.infantCount;
            this.travelerNames = traveler.travelerNames;
            this.travelerAges = traveler.travelerAges;
            this.passportNumbers = traveler.passportNumbers;
            return this;
        }

        public Traveler build() {
            return new Traveler(this);
        }
    }
}