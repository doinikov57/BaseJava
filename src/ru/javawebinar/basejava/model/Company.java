package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Company {
    private final Link homePage;
    private final List<Period> periods;

    public Company(String companyName, String url, List<Period> periods) {
        Objects.requireNonNull(companyName,"companyName could not be null");
        this.periods = Objects.requireNonNull(periods,
                "periods could not be null");
        this.homePage = new Link(companyName, url);
    }

    public String getCompanyName() {
        return homePage.getName();
    }

    public String getUrl() {
        return homePage.getUrl();
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) &&
                Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    public static class Period {
        private final LocalDate periodStart;
        private final LocalDate periodEnd;
        private final String position;
        private final String description;

        public Period(LocalDate periodStart, LocalDate periodEnd, String position, String description) {
            this.periodStart = Objects.requireNonNull(periodStart,
                    "periodStart could not be null");
            this.periodEnd = Objects.requireNonNull(periodEnd,
                    "periodEnd could not be null");
            this.position = Objects.requireNonNull(position,
                    "position could not be null");
            this.description = position;
        }

        public LocalDate getPeriodStart() {
            return periodStart;
        }

        public LocalDate getPeriodEnd() {
            return periodEnd;
        }

        public String getPosition() {
            return position;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return periodStart + " - " + periodEnd + "  " + position + "\n" + description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return periodStart.equals(period.periodStart) &&
                    periodEnd.equals(period.periodEnd) &&
                    position.equals(period.position) &&
                    description.equals(period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(periodStart, periodEnd, position, description);
        }
    }
}
