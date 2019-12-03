package ru.javawebinar.basejava.storage;//import java.lang.reflect.Array;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based  storage for Resumes
 */
public class ArrayStorage {
    private int numOfResumes;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        // clear all the references to Resume objects
        if (numOfResumes > 0) {
            Arrays.fill(storage, 0, numOfResumes - 1, null);
        }
        numOfResumes = 0;
    }

    public void save(Resume r) {
        if (numOfResumes < storage.length) {
            // check if uuid of the resume r is already used
            int index = uuidPosition(r.getUuid());
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

    public void update(Resume r) {
        // check if uuid of the resume r is present
        int index = uuidPosition(r.getUuid());
        if (index > -1) {
            // update  the resume
            storage[index] = r;
        } else {
            System.out.println(" update ERROR: Resume not present UUID" + r.getUuid());
        }
    }


    public Resume get(String uuid) {
        Resume resume = null;
        // check if resume with the uuid is present
        int index = uuidPosition(uuid);
        if (index > -1) {
            // resume uuid is present
            resume = storage[index];
        } else {
            System.out.println(" ERROR: Resume not present UUID " + uuid);
        }
        return resume;
    }

    public void delete(String uuid) {
        // check if resume is present
        int index = uuidPosition(uuid);
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

    /*
     * @return int index (position) of the resume with the uuid in the storage
     * @return -1 if resume with id is not present in the storage
     */
    private int uuidPosition(String uuid) {
        int index = -1;
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
