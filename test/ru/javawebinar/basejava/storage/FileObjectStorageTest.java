package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.ObjectStreamSerializer;

import java.io.File;

public class FileObjectStorageTest extends AbstractStorageTest {
    public FileObjectStorageTest() {
        super(new FileStorage(new File (RESUME_DIR), new ObjectStreamSerializer() {
        }));
    }
}