package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.XmlStreamSerializer;

public class PathXmlStorageTest extends AbstractStorageTest {
    protected static final String RESUME_DIR = "C:\\Users\\doinikov\\basejava\\ResumeDir";

    public PathXmlStorageTest() {
        super((new PathStorage(RESUME_DIR, new XmlStreamSerializer())));
    }
}