package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    private Link homePage;
    private List<Position> positions;

    private static final long serialVersionUID = 1L;

    public Company() {
    }
    public Company(String companyName, Position... positions) {
        this(new Link(companyName, ""), Arrays.asList(positions));
    }

    public Company(String companyName, String url, Position... positions) {
        this(new Link(companyName, url), Arrays.asList(positions));
    }

    public Company(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate periodStart;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate periodEnd;
        private String jobTitle;
        private String description;

        public Position() {
        }

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
            this.description = description == null?  "" : description;
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
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return periodStart.equals(position.periodStart) &&
                    periodEnd.equals(position.periodEnd) &&
                    jobTitle.equals(position.jobTitle) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(periodStart, periodEnd, jobTitle, description);
        }
    }
}
