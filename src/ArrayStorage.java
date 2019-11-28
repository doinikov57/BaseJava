//import java.lang.reflect.Array;

import java.util.Arrays;

/**
 * Array based  storage for Resumes
 */
public class ArrayStorage {
    private int numOfResumes;
    private Resume[] storage = new Resume[10000];

    void clear() {
        // clear all the references to Resume objects
        for (int i = 0; i < numOfResumes; i++) {
            storage[i] = null;
        }
        numOfResumes = 0;
    }

    void save(Resume r) {
        if (numOfResumes < storage.length) {
            storage[numOfResumes] = r;
            numOfResumes++;
        } else {
            System.out.println("Storage is full!");
        }
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int i;
        int index = -1; // uuid not found
        for (i = 0; i < numOfResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
                break;
            }
        }
        // if uuid is found at [index]
        if (index > -1) {
            System.out.println("Delete uuid:" + uuid + " index:" + index + " num:" + numOfResumes);
            for (i = index; i < numOfResumes - 1; i++) {
                storage[i] = storage[i + 1];
            }
            numOfResumes--;
            storage[storage.length - 1] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numOfResumes);
    }

    int size() {
        return numOfResumes;
    }
}
