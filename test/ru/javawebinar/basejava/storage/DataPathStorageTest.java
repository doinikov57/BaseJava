package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    protected static final String RESUME_DIR = "C:\\Users\\doinikov\\basejava\\ResumeDir";

    public DataPathStorageTest() {
        super((new PathStorage(RESUME_DIR, new DataStreamSerializer())));
    }
}