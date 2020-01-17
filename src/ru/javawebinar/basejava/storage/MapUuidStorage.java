package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage <String>{

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
    protected void doSave(Resume resume, String keyUuid) {
        storage.put( keyUuid, resume);
    }

    @Override
    protected void doUpdate(String keyUuid, Resume resume) {
        storage.put( keyUuid, resume);
    }

    @Override
    protected Resume doGet(String keyUuid) {
        return storage.get( keyUuid);
    }

    @Override
    protected void doDelete(String keyUuid) {
        storage.remove( keyUuid);
    }

    @Override
    protected boolean isExist(String keyUuid) {
        return storage.containsKey( keyUuid);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }
}