package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
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

    public void cSave(Resume resume, Object keyIndexUuid) {
        if (numOfResumes > storage.length - 1) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, (Integer) keyIndexUuid);
            numOfResumes++;
        }
    }

    protected void cDelete(Object index) {
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

    public Object cGetIndexIfExist(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Object cGetIndexIfNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    public Resume pickResume(Object index) {
        return storage[(Integer) index];
    }

    protected void cUpdate(Object index, Resume resume) {
        storage[(Integer)index] = resume;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeletedResume(int index);
}