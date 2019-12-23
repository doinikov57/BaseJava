package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class MapStorageTest extends AbstractStorageTest{
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    @Override
    @Ignore("OverFlow is not tested for Map")
    public void saveOverflow() {}
}