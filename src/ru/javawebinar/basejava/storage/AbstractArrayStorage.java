package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes.
 */
public abstract class AbstractArrayStorage extends AbstractStorage <Integer>{

    protected static final int STORAGE_LIMIT = 10_000;
    protected int numOfResumes;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void insertResume(Resume resume, int index);

    protected abstract void fillDeletedResume(int index);

    public void clear() {
        if (numOfResumes > 0) Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    @Override
    public List<Resume> getResumeList() {
        return Arrays.asList(Arrays.copyOf(storage, numOfResumes));
    }

    @Override
    public int size() {
        return numOfResumes;
    }

    @Override
    protected void doSave(Resume resume, Integer searchKey) {
        if (numOfResumes > storage.length - 1) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertResume(resume, searchKey);
            numOfResumes++;
        }
    }

    @Override
    protected void doDelete(Integer searchKey) {
        fillDeletedResume(searchKey);
        storage[numOfResumes - 1] = null;
        numOfResumes--;
    }
        @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected boolean isExist(Integer keyIndex) {
        return (keyIndex > -1);
    }
}