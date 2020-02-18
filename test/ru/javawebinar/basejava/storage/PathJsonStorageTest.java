package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.JsonStreamSerialiser;

public class PathJsonStorageTest extends AbstractStorageTest {
    protected static final String RESUME_DIR = "C:\\Users\\doinikov\\basejava\\ResumeDir";

    public PathJsonStorageTest() {
        super((new PathStorage(RESUME_DIR, new JsonStreamSerialiser())));
    }
}
