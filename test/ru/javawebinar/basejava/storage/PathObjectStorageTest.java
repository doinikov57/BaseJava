package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.Serializer.ObjectStreamSerializer;

public class PathObjectStorageTest extends AbstractStorageTest {

    public PathObjectStorageTest() {
        super((new PathStorage(RESUME_DIR, new ObjectStreamSerializer())));
    }
}
