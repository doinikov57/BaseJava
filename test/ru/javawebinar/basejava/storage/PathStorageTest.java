package ru.javawebinar.basejava.storage;

public class PathStorageTest extends AbstractStorageTest {
    protected static final String RESUME_DIR = "C:\\Users\\doinikov\\basejava\\ResumeDir";

    public PathStorageTest() {
        super((new PathStorage(RESUME_DIR, new ObjectSerialization())));
    }
}
