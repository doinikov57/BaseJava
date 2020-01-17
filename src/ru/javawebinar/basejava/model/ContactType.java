package ru.javawebinar.basejava.model;

public enum ContactType {
    PHONE("Тел."),
    SKYPE("skype "),
    EMAIL("mail"),
    LINKEDIN("профиль"),
    GITHUB("профиль"),
    STACKOVERFLOW("профиль"),
    HOMEPAGE("Home Page");

    private String type;

    ContactType(String type) {
        this.type = type;
    }
}
