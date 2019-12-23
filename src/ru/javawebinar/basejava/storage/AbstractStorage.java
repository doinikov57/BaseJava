package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object keyIndexUuid = cGetIndexIfNotExist(resume);
        doSave(resume, keyIndexUuid);
    }

    public void update(Resume resume) {
        Object keyIndexUuid = cGetIndexIfExist(resume);
        doUpdate(keyIndexUuid, resume);
    }

    public Resume get(String uuid) {
        return pickResume(cGetIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(cGetIndexIfExist(uuid));
    }

    public abstract void doSave(Resume resume, Object keyIndexUuid);

    protected abstract void doUpdate(Object keyIndexUuid, Resume resume);

    protected abstract void doDelete(Object keyIndexUuid);

    private Object cGetIndexIfExist(Resume resume) {
        return cGetIndexIfExist(resume.getUuid());
    }

    private Object cGetIndexIfNotExist(Resume resume) {
        return cGetIndexIfNotExist(resume.getUuid());
    }

//    protected abstract Object cGetIndexIfExist(String uuid);
//
//    protected abstract Object cGetIndexIfNotExist(String uuid);

    protected abstract boolean isExist(Object keyIndex);

    protected abstract Object getIndex(String uuid);

    private Object cGetIndexIfExist(String uuid) {
        Object keyIndex = getIndex(uuid);
        if (isExist(keyIndex)) return keyIndex;
        else throw new NotExistStorageException(uuid);
    }

    private Object cGetIndexIfNotExist(String uuid) {
        Object keyIndex = getIndex(uuid);
        if (isExist(keyIndex)) throw new ExistStorageException(uuid);
        else return keyIndex;
    }

    protected abstract Resume pickResume(Object keyIndexUuid);
}