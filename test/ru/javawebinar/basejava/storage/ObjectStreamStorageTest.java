package ru.javawebinar.basejava.storage;

import java.io.File;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    protected static final File RESUME_DIR = new File ("C:\\Users\\doinikov\\basejava\\ResumeDir");
    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(RESUME_DIR));
    }
}