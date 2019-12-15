package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.Exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

    private static final int STORAGE_LIMIT = 10_000;
    protected Storage storage = new ArrayStorage();
    protected final static String UUID_1 = "uuid1";
    protected final static String UUID_2 = "uuid2";
    protected final static String UUID_3 = "uuid3";
    protected static final String NEW_UUID = "New UUID";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setup() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume resumeTestSV = new Resume(NEW_UUID);
        storage.save(resumeTestSV);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resumeTestSV, storage.get(NEW_UUID));
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT + 1; i++) {
            storage.save(new Resume());
        }
    }

    @Test
    public void update() {
        Resume resumeTestUD = new Resume(UUID_2);
        storage.update(resumeTestUD);
        Assert.assertEquals(resumeTestUD, storage.get(UUID_2));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Resume[] resumeTestDel = {new Resume(UUID_1), new Resume(UUID_3)};
        Assert.assertArrayEquals(resumeTestDel, storage.getAll());
        Assert.assertEquals(2, storage.size());
        storage.delete(UUID_1);
        storage.delete(UUID_3);
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumeTestGA = {new Resume(UUID_1), new Resume(UUID_2),
                new Resume(UUID_3)};
        Assert.assertArrayEquals(resumeTestGA, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(NEW_UUID);
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NEW_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(NEW_UUID));
    }

}
