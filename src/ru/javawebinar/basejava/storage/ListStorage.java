package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    public int size() {
        return storage.size();
    }

    @Override
    protected List<Resume> getResumeList() {
        return storage;
    }

    @Override
    protected void doSave(Resume resume, Object keyIndexUuid) {
        storage.add(resume);
    }

    protected void doUpdate(Object index, Resume resume) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((Integer) index);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected boolean isExist(Object keyIndex) {
        return keyIndex != null;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int index = 0;
        for (Resume r : storage) {
            if (r.getUuid().equals(uuid)) {
                return index;
            }
            index++;
        }
        return null;
    }
}