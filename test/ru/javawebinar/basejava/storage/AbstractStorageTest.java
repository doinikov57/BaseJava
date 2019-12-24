package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorageTest {

    private Storage storage;
    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String NEW_UUID = "New UUID";

    private final static Resume RESUME_1 = new Resume(UUID_1);
    private final static Resume RESUME_2 = new Resume(UUID_2);
    private final static Resume RESUME_3 = new Resume(UUID_3);
    private final static Resume RESUME_4 = new Resume(NEW_UUID);

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        Resume resumeTestSV = RESUME_4;
        storage.save(resumeTestSV);
        assertSize(4);
        Assert.assertEquals(resumeTestSV, storage.get(NEW_UUID));
    }

    @Test
    public void update() {
        Resume ResumeTestUD = new Resume(UUID_2);
        storage.update(ResumeTestUD);
        Assert.assertSame(ResumeTestUD, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test
    public void getAll() {
        Resume[] resumeTestGA = {RESUME_1, RESUME_2, RESUME_3};
        Resume[] resumeGA = storage.getAll();
        Assert.assertEquals(RESUME_1, resumeGA[0]);
        Assert.assertEquals(RESUME_2, resumeGA[1]);
        Assert.assertEquals(RESUME_3, resumeGA[2]);
        assertSize(resumeGA.length);
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
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(NEW_UUID);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    private void assertSize (int size){
        Assert.assertEquals(size, storage.size());
    }
}