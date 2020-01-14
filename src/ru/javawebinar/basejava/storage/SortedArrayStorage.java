package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;

import static java.util.Arrays.binarySearch;

/**
 * Sorted Array based storage for Resumes.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> UUID_COMPARATOR =
            (n1, n2) -> n1.getUuid().compareTo(n2.getUuid());

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        return binarySearch(storage, 0, numOfResumes,
                searchKey, UUID_COMPARATOR);
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