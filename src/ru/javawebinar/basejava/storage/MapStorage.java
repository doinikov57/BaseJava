package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
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
