package ru.javawebinar.basejava.model;

/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    public Resume(String id) {
        uuid = id;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }
}
