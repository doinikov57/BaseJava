package ru.javawebinar.basejava.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    protected static final String RESUME_DIR = "C:\\Users\\doinikov\\basejava\\ResumeDir";

    public ObjectStreamPathStorageTest() {
        super((new ObjectStreamPathStorage(RESUME_DIR)));
    }
}
