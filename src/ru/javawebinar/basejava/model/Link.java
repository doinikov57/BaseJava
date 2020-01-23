package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Link {
    private String name;
    private String url;

    public Link(String name, String url) {
        this.name = Objects.requireNonNull(name,"companyName could not be null");
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return name.equals(link.name) &&
                Objects.equals(url, link.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
