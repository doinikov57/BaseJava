package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;

public class ListStorageTest extends AbstractStorageTest{
    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    @Override
    @Ignore ("OverFlow is not tested for List")
    public void saveOverflow() {
    }
}