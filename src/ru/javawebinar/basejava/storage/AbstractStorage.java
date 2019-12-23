package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doSave(Resume resume, Object keyIndexUuid);

    protected abstract void doUpdate(Object keyIndexUuid, Resume resume);

    protected abstract void doDelete(Object keyIndexUuid);

    protected abstract boolean isExist(Object keyIndex);

    protected abstract Object getIndex(String uuid);

    protected abstract Resume doGet(Object keyIndexUuid);

    public void save(Resume resume) {
        Object keyIndexUuid = getIndexIfNotExist(resume);
        doSave(resume, keyIndexUuid);
    }

    public void update(Resume resume) {
        Object keyIndexUuid = getIndexIfExist(resume);
        doUpdate(keyIndexUuid, resume);
    }

    public Resume get(String uuid) {
        return doGet(getIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(getIndexIfExist(uuid));
    }

    private Object getIndexIfExist(Resume resume) {
        return getIndexIfExist(resume.getUuid());
    }

    private Object getIndexIfNotExist(Resume resume) {
        return getIndexIfNotExist(resume.getUuid());
    }

    private Object getIndexIfExist(String uuid) {
        Object keyIndex = getIndex(uuid);
        if (isExist(keyIndex)) return keyIndex;
        else throw new NotExistStorageException(uuid);
    }

    private Object getIndexIfNotExist(String uuid) {
        Object keyIndex = getIndex(uuid);
        if (isExist(keyIndex)) throw new ExistStorageException(uuid);
        else return keyIndex;
    }
}