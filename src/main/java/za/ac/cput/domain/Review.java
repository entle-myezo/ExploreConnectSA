package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private int rating;
    private String comment;
    private LocalDateTime date;
    private String serviceType;
    private String serviceId;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Customer reviewer;
    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Booking booking;

    protected Review(){}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.date = builder.date;
        this.serviceType = builder.serviceType;
        this.serviceId = builder.serviceId;
        this.reviewer = builder.reviewer;
        this.booking = builder.booking;
    }

    // Getters
    public Long getReviewId() { return reviewId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getDate() { return date; }
    public String getServiceType() { return serviceType; }
    public String getServiceId() { return serviceId; }
    public Customer getReviewer() { return reviewer; }
    public Booking getBooking() { return booking; }

    // Business methods
    public boolean validateReview() {
        return rating >= 1 && rating <= 5;
    }

    public boolean flagInappropriate() {
        // Implementation for flagging inappropriate reviews
        return true;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", serviceType='" + serviceType + '\'' +
                ", reviewer=" + (reviewer != null ? reviewer.getFullName() : "Anonymous") +
                '}';
    }

    public static class Builder {
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDateTime date;
        private String serviceType;
        private String serviceId;
        private Customer reviewer;
        private Booking booking;


        public Builder setServiceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder setServiceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.date = review.date;
            this.serviceType = review.serviceType;
            this.serviceId = review.serviceId;
            this.reviewer = review.reviewer;
            this.booking = review.booking;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}