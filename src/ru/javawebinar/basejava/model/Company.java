package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private String companyName;
    private String url;
    private List< Period> periods;
    class Period {
        LocalDate periodStart;
        LocalDate periodEnd;
        String position;
    }
    private String description;

    public Company(String companyName, String url, List<Period> periods, String description) {
        this.companyName = companyName;
        this.url = url;
        this.periods = periods;
        this.description = description;
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
}
