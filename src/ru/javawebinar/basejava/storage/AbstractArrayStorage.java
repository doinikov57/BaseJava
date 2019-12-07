package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */
public abstract class AbstractArrayStorage implements Storage {

    private static final int STORAGE_LIMIT = 10000;
    protected int numOfResumes;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return numOfResumes;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage[index];
        } else {
            System.out.println(" ERROR: Resume not present UUID " + uuid);
            return null;
        }
    }

    protected abstract int getIndex(String uuid);
}
