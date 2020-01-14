package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract void doSave(Resume resume, Object keyIndexUuid);

    protected abstract void doUpdate(Object keyIndexUuid, Resume resume);

    protected abstract void doDelete(Object keyIndexUuid);

    protected abstract boolean isExist(Object keyIndex);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume doGet(Object keyIndexUuid);

    protected abstract List<Resume> getResumeList();

    @Override
    public void save(Resume resume) {
        Object keyIndexUuid = getIndexIfNotExist(resume.getUuid());
        doSave(resume, keyIndexUuid);
    }

    @Override
    public void update(Resume resume) {
        Object keyIndexUuid = getIndexIfExist(resume.getUuid());
        doUpdate(keyIndexUuid, resume);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getIndexIfExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getIndexIfExist(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getResumeList();
        Collections.sort(list);
        return list;
    }

    private Object getIndexIfExist(String uuid) {
        Object keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) return keyIndex;
        else throw new NotExistStorageException(uuid);
    }

    private Object getIndexIfNotExist(String uuid) {
        Object keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) throw new ExistStorageException(uuid);
        else return keyIndex;
    }
}