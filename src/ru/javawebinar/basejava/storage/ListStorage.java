package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage <Integer>{

    private List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected List<Resume> getResumeList() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void doSave(Resume resume, Integer keyIndexUuid) {
        storage.add(resume);
    }

    @Override
    protected void doUpdate(Integer index, Resume resume) {
        storage.set( index, resume);
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage.get( index);
    }

    @Override
    protected void doDelete(Integer index) {
        storage.remove(( index).intValue());
    }

    @Override
    protected boolean isExist(Integer keyIndex) {
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