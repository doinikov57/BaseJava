/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private int numOfResumes;
    Resume[] storage = new Resume[10000];

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
        int i;
        for (i = 0; i <= numOfResumes - 1; i++) {
            if (storage[i].uuid == uuid) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        int i;
        int index = -1; // uuid not found
        for (i = 0; i <= numOfResumes - 1; i++) {
            if (storage[i].uuid == uuid) {
                // in case it is the last array element: i = storage.length -1
                storage[i] = null;
                index = i;
                i = numOfResumes; // to leave the for cycle
            }
        }
        // if uuid is found at [index]
        if (index >= 0) {
//                System.out.println("Delete uuid:"+ uuid +" index:" + index +" num:" + numOfResumes);
            for (i = index; i < numOfResumes - 1; i++) {
                storage[i] = storage[i + 1];
            }
            numOfResumes--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[numOfResumes];
        for (int i = 0; i < numOfResumes; i++) {
            allResumes[i] = storage[i];
        }
        return allResumes;
    }

    int size() {
        return numOfResumes;
    }
}
