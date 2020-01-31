package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

public class Company {
    private final Link homePage;
    private final List<Position> positions;

    public Company(String companyName, String url, Position... positions) {
        this(new Link(companyName, url), Arrays.asList(positions));
    }

    public Company(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public String getCompanyName() {
        return homePage.getName();
    }

    public String getUrl() {
        return homePage.getUrl();
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "Company(" + homePage + "," + positions + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) &&
                Objects.equals(positions, company.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    public static class Position {
        private final LocalDate periodStart;
        private final LocalDate periodEnd;
        private final String jobTitle;
        private final String description;

        public Position(int yearStart, Month monthStart, int yearEnd, Month monthEnd,
                        String jobTitle, String description) {
            this(DateUtil.of(yearStart, monthStart), DateUtil.of(yearEnd, monthEnd),
                    jobTitle, description);
        }

        public Position(int yearStart, Month monthStart, String jobTitle, String description) {
            this(DateUtil.of(yearStart, monthStart), NOW, jobTitle, description);
        }

        public Position(LocalDate periodStart, LocalDate periodEnd, String position, String description) {
            this.periodStart = Objects.requireNonNull(periodStart,
                    "periodStart could not be null");
            this.periodEnd = Objects.requireNonNull(periodEnd,
                    "periodEnd could not be null");
            this.jobTitle = Objects.requireNonNull(position,
                    "jobTitle could not be null");
            this.description = description;
        }

        public LocalDate getPeriodStart() {
            return periodStart;
        }

        public LocalDate getPeriodEnd() {
            return periodEnd;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return periodStart + " - " + periodEnd + "  " + jobTitle + "\n" + description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return periodStart.equals(position.periodStart) &&
                    periodEnd.equals(position.periodEnd) &&
                    this.jobTitle.equals(position.jobTitle) &&
                    description.equals(position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(periodStart, periodEnd, jobTitle, description);
        }
    }
}
