package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.*;
@Entity
public class Admin extends User {
    @Id
    private String empId;
    private String department;
    private String accessLevel;
    private LocalDateTime hireDate;

    // Relationships
    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> generatedReports;
    @OneToMany(mappedBy = "bookedBy")
    private List<Booking> managedBookings;

    protected Admin(){}

    private Admin(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.isActive = builder.isActive;
        this.createdAt = builder.createdAt;
        this.lastLogin = builder.lastLogin;
        // Admin specific fields
        this.empId = builder.empId;
        this.department = builder.department;
        this.accessLevel = builder.accessLevel;
        this.hireDate = builder.hireDate;

        // Collections
        this.generatedReports = builder.generatedReports != null ? builder.generatedReports : new ArrayList<>();
        this.managedBookings = builder.managedBookings != null ? builder.managedBookings : new ArrayList<>();
    }

    // Getters
    public String getEmpId() { return empId; }
    public String getDepartment() { return department; }
    public String getAccessLevel() { return accessLevel; }
    public LocalDateTime getHireDate() { return hireDate; }
    public List<Report> getGeneratedReports() { return generatedReports; }
    public List<Booking> getManagedBookings() { return managedBookings; }

    // Admin methods
    public void manageUsers() {
        // Implementation for user management
    }

    public List<Booking> viewAllBookings() {
        return managedBookings;
    }

    public Report generateReport(Report report) {
        this.generatedReports.add(report);
        return report;
    }

    public void updateSystemSettings() {
        // Implementation for system settings
    }

    @Override
    public String toString() {
        return "Admin{" +
                "empId='" + empId + '\'' +
                ", department='" + department + '\'' +
                ", accessLevel='" + accessLevel + '\'' +
                ", hireDate=" + hireDate +
                ", generatedReports=" + generatedReports.size() +
                ", managedBookings=" + managedBookings.size() +
                '}';
    }

    public static class Builder {
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private UserRole role;
        private boolean isActive;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime lastLogin;

        private String empId;
        private String department;
        private String accessLevel;
        private LocalDateTime hireDate;
        private List<Report> generatedReports;
        private List<Booking> managedBookings;

        public Builder() {
            this.role = UserRole.ADMIN;
            this.hireDate = LocalDateTime.now();
        }
        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public Builder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setLastLogin(LocalDateTime lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public Builder setEmpId(String empId){
          this.empId = empId;
          return this;
      }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setAccessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public Builder setHireDate(LocalDateTime hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder setGeneratedReports(List<Report> generatedReports) {
            this.generatedReports = generatedReports;
            return this;
        }

        public Builder setManagedBookings(List<Booking> managedBookings) {
            this.managedBookings = managedBookings;
            return this;
        }


        public Builder copy(Admin admin) {
            this.userId = admin.userId;
            this.firstName = admin.firstName;
            this.lastName = admin.lastName;
            this.email = admin.email;
            this.password = admin.password;
            this.role = admin.role;
            this.isActive = admin.isActive;
            this.createdAt = admin.createdAt;
            this.lastLogin = admin.lastLogin;
            this.empId = admin.empId;
            this.department = admin.department;
            this.accessLevel = admin.accessLevel;
            this.hireDate = admin.hireDate;
            this.generatedReports = admin.generatedReports;
            this.managedBookings = admin.managedBookings;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
