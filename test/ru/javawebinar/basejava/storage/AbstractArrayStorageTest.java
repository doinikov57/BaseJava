package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.Exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.fail;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    private Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) storage.save(new Resume());
        } catch (StorageException e) {
            fail("Exception " + e.getMessage() + " should not be thrown");
        }
        storage.save(new Resume());
    }
}