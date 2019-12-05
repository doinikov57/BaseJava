package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private int numOfResumes;
    private Resume[] storage = new Resume[10000];

    /**
     * clear storage.
     * (clear all the references to Resume objects)
     */
    public void clear() {
         if (numOfResumes > 0) {
            Arrays.fill(storage, 0, numOfResumes - 1, null);
        }
        numOfResumes = 0;
    }

    /**
     * add Resume r
     * check if storage is not full
     * check if uuid already used
     */
    public void save(Resume r) {
        if (numOfResumes < storage.length) {
            // check if uuid of the resume r is already used
            int index = getIndex(r.getUuid());
            if (index > -1) {
                // resume with the uuid is already used
                System.out.println(" ERROR: already used UUID: " + r.getUuid());
            } else {
                // add resume to the storage
                storage[numOfResumes] = r;
                numOfResumes++;
            }
        } else {
            System.out.println("ERROR: Storage is full!");
        }
    }

    /**
     * update Resume with the @uuid if exists
     */
    public void update(Resume r) {
        // check if uuid of the resume r is present
        int index = getIndex(r.getUuid());
        if (index > -1) {
            // update  the resume
            storage[index] = r;
        } else {
            System.out.println(" update ERROR: Resume not present UUID" + r.getUuid());
        }
    }

    /**
     * @return  Resume with the @uuid if exists
     */
    public Resume get(String uuid) {
        Resume resume = null;
        // check if resume with the uuid is present
        int index = getIndex(uuid);
        if (index > -1) {
            // resume uuid is present
            resume = storage[index];
        } else {
            System.out.println(" ERROR: Resume not present UUID " + uuid);
        }
        return resume;
    }

    /**
     * delete Resume with the @uuid
     */
    public void delete(String uuid) {
        // check if resume is present
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[numOfResumes - 1];
            storage[numOfResumes - 1] = null;
            numOfResumes--;
        } else {
            System.out.println(" ERROR: Resume not present UUID " + uuid);
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

    /**
     * @return int index (position) of the resume with the uuid in the storage
     *          -1 if resume with uuid is not present
     */
    private int getIndex(String uuid) {
        // int index = -1;
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
