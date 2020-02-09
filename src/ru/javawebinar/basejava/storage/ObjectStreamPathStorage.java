package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir) {
        super(dir);
    }

    @Override
    protected void writeFile(Resume resume, OutputStream os) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)){
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume readFile(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)){
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume ", e.getMessage(), e);
        }
    }
}
