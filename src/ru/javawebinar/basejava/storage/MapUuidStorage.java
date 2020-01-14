package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getResumeList() {
        return new ArrayList<>(storage.values());
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
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}