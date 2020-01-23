package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Company {
    private final String companyName;
    private final String url;
    private final List<Period> periods;
    private final String description;

    public Company(String companyName, String url, List<Period> periods, String description) {
        this.companyName = Objects.requireNonNull(companyName,
                "companyName could not be null");
        this.url = url;
        this.periods = Objects.requireNonNull(periods,
                "periods could not be null");
        this.description = Objects.requireNonNull(description,
                "description could not be null");
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUrl() {
        return url;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", periods=" + periods +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return companyName.equals(company.companyName) &&
                Objects.equals(url, company.url) &&
                periods.equals(company.periods) &&
                description.equals(company.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, url, periods, description);
    }

    public static class Period {
        LocalDate periodStart;
        LocalDate periodEnd;
        String position;

        public Period(LocalDate periodStart, LocalDate periodEnd, String position) {
            this.periodStart = Objects.requireNonNull(periodStart,
                    "periodStart could not be null");
            this.periodEnd = Objects.requireNonNull(periodEnd,
                    "periodEnd could not be null");
            this.position = Objects.requireNonNull(position,
                    "position could not ");
        }

        public LocalDate getPeriodStart() {
            return periodStart;
        }

        public void setPeriodStart(LocalDate periodStart) {
            this.periodStart = periodStart;
        }

        public LocalDate getPeriodEnd() {
            return periodEnd;
        }

        public void setPeriodEnd(LocalDate periodEnd) {
            this.periodEnd = periodEnd;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return  periodStart + " - " + periodEnd + "  " + position;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Period)) return false;
            Period period = (Period) o;
            return periodStart.equals(period.periodStart) &&
                    periodEnd.equals(period.periodEnd) &&
                    position.equals(period.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(periodStart, periodEnd, position);
        }
    }
}
