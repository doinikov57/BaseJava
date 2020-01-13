package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getResumeList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doSave(Resume resume, Object keyName) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Object keyName, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object keyName) {
        return (Resume) keyName;
    }

    @Override
    protected void doDelete(Object keyName) {
        storage.remove((((Resume) keyName).getUuid()));
    }

    @Override
    protected boolean isExist(Object keyName) {
        return !(keyName == null);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}