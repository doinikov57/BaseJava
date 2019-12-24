package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.util.Arrays.binarySearch;

/**
 * Sorted Array based storage for Resumes.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return binarySearch(storage, 0, numOfResumes, searchKey, UUID_COMPARATOR);
    }

    @Override
    protected void insertResume(Resume resume, int index) {
//      http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, numOfResumes - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void fillDeletedResume(int index) {
        int numMoved = numOfResumes - index - 1;
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }
}