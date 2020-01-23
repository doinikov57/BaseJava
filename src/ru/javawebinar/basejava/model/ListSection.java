package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private List<String> paragraphs;

    public ListSection(List<String> paragraphs) {
        this.paragraphs = Objects.requireNonNull(paragraphs,
                "paragraphs could not be null");
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection)) return false;
        ListSection that = (ListSection) o;
        return paragraphs.equals(that.paragraphs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paragraphs);
    }

    @Override
    public String toString() {
        return paragraphs.toString();
    }

}