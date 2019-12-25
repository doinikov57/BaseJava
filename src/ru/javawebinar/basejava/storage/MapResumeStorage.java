package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private Map<Resume, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<Resume>(storage.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doSave(Resume resume, Object keyName) {
        storage.put((Resume) resume, resume);
    }

    @Override
    protected void doUpdate(Object keyName, Resume resume) {
        storage.put((Resume) keyName, resume);
    }

    @Override
    protected Resume doGet(Object keyName) {
        return storage.get((Resume) keyName);
    }

    @Override
    protected void doDelete(Object keyName) {
        storage.remove((Resume) keyName);
    }

    @Override
    protected boolean isExist(Object keyName) {
        return storage.containsKey((Resume) keyName);
    }

    @Override
    protected Object getIndex(String uuid) {
        for (Resume key : storage.keySet()) {
            if (key.getUuid().equals(uuid)) return key;
        }
        return null;
    }
}