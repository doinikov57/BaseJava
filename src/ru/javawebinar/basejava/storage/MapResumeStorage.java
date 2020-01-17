package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage <Resume> {

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
    protected void doSave(Resume resume, Resume keyName) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume keyName, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume keyName) {
        return  keyName;
    }

    @Override
    protected void doDelete(Resume keyName) {
        storage.remove((( keyName).getUuid()));
    }

    @Override
    protected boolean isExist(Resume keyName) {
        return (keyName != null);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }
}