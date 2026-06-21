package za.ac.cput.domain;

import jakarta.persistence.*;
import za.ac.cput.util.IdGenerator;

import java.io.File;
import java.time.LocalDateTime;

@Entity
public class Report {
    @Id
    private String reportId;
    private String reportType;
    private LocalDateTime generatedDate;
    private String generatedBy;
    @Lob
    private byte[] data;
    private String format;

    protected Report(){}

    private Report(Builder builder) {
        this.reportId = builder.reportId;
        this.reportType = builder.reportType;
        this.generatedDate = builder.generatedDate;
        this.generatedBy = builder.generatedBy;
        this.data = builder.data;
        this.format = builder.format;
    }

    // Getters
    public String getReportId() { return reportId; }
    public String getReportType() { return reportType; }
    public LocalDateTime getGeneratedDate() { return generatedDate; }
    public String getGeneratedBy() { return generatedBy; }
    public byte[] getData() { return data; }
    public String getFormat() { return format; }

    // Business methods
    public void generate() {
        this.generatedDate = LocalDateTime.now();
        // Implementation for report generation
    }

    public File export() {
        // Implementation for exporting report
        return new File(reportId + "." + format.toLowerCase());
    }

    public void email(String recipient) {
        // Implementation for emailing report
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", generatedDate=" + generatedDate +
                ", generatedBy='" + generatedBy + '\'' +
                ", format='" + format + '\'' +
                '}';
    }

    public static class Builder {
        private String reportId;
        private String reportType;
        private LocalDateTime generatedDate;
        private String generatedBy;
        private byte[] data;
        private String format;

        public Builder setReportId(String reportId) {
            this.reportId = reportId;
            return this;
        }

        public Builder setReportType(String reportType) {
            this.reportType = reportType;
            return this;
        }

        public Builder setGeneratedDate(LocalDateTime generatedDate) {
            this.generatedDate = generatedDate;
            return this;
        }

        public Builder setGeneratedBy(String generatedBy) {
            this.generatedBy = generatedBy;
            return this;
        }

        public Builder setData(byte[] data) {
            this.data = data;
            return this;
        }

        public Builder setFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder copy(Report report) {
            this.reportId = report.reportId;
            this.reportType = report.reportType;
            this.generatedDate = report.generatedDate;
            this.generatedBy = report.generatedBy;
            this.data = report.data;
            this.format = report.format;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }
}