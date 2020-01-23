package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {
    private String text;

    public TextSection(String text) {
        this.text = Objects.requireNonNull(text,
                "text in the TextSection could not be null");
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection)) return false;
        TextSection that = (TextSection) o;
        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text;
    }
}
