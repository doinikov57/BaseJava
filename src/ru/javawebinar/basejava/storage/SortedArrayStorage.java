package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Arrays.binarySearch;

/**
 * Sorted Array based storage for Resumes.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return binarySearch(storage,0, numOfResumes, searchKey);
    }

}
