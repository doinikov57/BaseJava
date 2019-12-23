package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected int numOfResumes;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        if (numOfResumes > 0) Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    public void doSave(Resume resume, Object keyIndexUuid) {
        if (numOfResumes > storage.length - 1) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, (Integer) keyIndexUuid);
            numOfResumes++;
        }
    }

    protected void doDelete(Object index) {
        fillDeletedResume((Integer) index);
        storage[numOfResumes - 1] = null;
        numOfResumes--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, numOfResumes);
    }

    public int size() {
        return numOfResumes;
    }

    public Resume pickResume(Object index) {
        return storage[(Integer) index];
    }

    protected void doUpdate(Object index, Resume resume) {
        storage[(Integer) index] = resume;
    }

    protected abstract Object getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeletedResume(int index);

    @Override
    protected boolean isExist(Object keyIndex) {
        return ((Integer) keyIndex > -1);
    }
}