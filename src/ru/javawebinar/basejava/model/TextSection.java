package ru.javawebinar.basejava.model;

public class TextSection extends Section {
    private String text;

    public TextSection(String text)
    {
        this.text = text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}