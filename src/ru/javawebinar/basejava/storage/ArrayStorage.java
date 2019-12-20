package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume, int index) {
        storage[numOfResumes] = resume;
    }

    @Override
    protected void fillDeletedResume(int index) {
        storage[index] = storage[numOfResumes - 1];
    }

}

//    @Override
//    public int cGetIndex(Object uuidKey) {
//        return 0;
//    }
//
//    @Override
//    public Resume pickResume(Object IndexUuid) {
//        return null;
//    }