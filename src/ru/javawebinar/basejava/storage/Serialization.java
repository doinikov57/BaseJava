package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serialization {

    void writeFile(Resume r, OutputStream os) throws IOException;

    Resume readFile(InputStream is) throws IOException;
}
