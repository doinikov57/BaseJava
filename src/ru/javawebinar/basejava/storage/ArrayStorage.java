package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        if (numOfResumes > 0) Arrays.fill(storage, 0, numOfResumes, null);
        numOfResumes = 0;
    }

    public void save(Resume resume) {
        if (numOfResumes < storage.length) {
            int index = getIndex(resume.getUuid());
            if (index > -1) {
                System.out.println(" ERROR: already used UUID: " + resume.getUuid());
            } else {
                storage[numOfResumes] = resume;
                numOfResumes++;
            }
        } else {
            System.out.println("ERROR: Storage is full!");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println(" update ERROR: Resume not present UUID" + resume.getUuid());
        }
    }

    public void delete(String uuid) {
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

    protected int getIndex(String uuid) {
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
