package ru.javawebinar.basejava.model;

import java.util.List;

public class CompanySection extends Section {
    private List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}';
    }
}

