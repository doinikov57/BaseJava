package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.Exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected int numOfResumes;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        if (numOfResumes > 0) Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    public void save(Resume resume) {
        if (numOfResumes < storage.length) {
            int index = getIndex(resume.getUuid());
            if (index > -1) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                insertResume(resume, index);
                numOfResumes++;
            }
        } else {
            throw new StorageException("Storage overflow",resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            fillDeletedResume(index);
            storage[numOfResumes - 1] = null;
            numOfResumes--;
        } else {
            throw new NotExistStorageException(uuid);
        }
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeletedResume(int index);
}
