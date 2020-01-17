package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends Section {

    private List<String> paragraphs;

    public ListSection(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public List<String> getParagraphs() {
        return paragraphs;
    }
}
