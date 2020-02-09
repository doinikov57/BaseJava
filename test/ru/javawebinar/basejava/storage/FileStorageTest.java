package ru.javawebinar.basejava.storage;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest {
    protected static final File RESUME_DIR = new File ("C:\\Users\\doinikov\\basejava\\ResumeDir");
    public FileStorageTest() {
        super(new FileStorage(RESUME_DIR, new ObjectSerialization() {
        }));
    }
}