package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectSerialization implements Serialization {

    @Override
    public void writeFile(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume readFile(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return ((Resume) ois.readObject());
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume ", e);
        }
    }
}