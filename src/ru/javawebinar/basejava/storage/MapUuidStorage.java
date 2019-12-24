package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>(storage.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doSave(Resume resume, Object keyUuid) {

        storage.put((String) keyUuid, resume);
    }

    @Override
    protected void doUpdate(Object keyUuid, Resume resume) {
        storage.put((String) keyUuid, resume);
    }

    @Override
    protected Resume doGet(Object keyUuid) {
        return storage.get((String) keyUuid);
    }

    @Override
    protected void doDelete(Object keyUuid) {
        storage.remove((String) keyUuid);
    }

    @Override
    protected boolean isExist(Object keyUuid) {
        return storage.containsKey((String) keyUuid);
    }

    @Override
    protected String getIndex(String uuid) {
        return uuid;
    }
}
