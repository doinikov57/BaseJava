package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    protected static final Comparator<Resume> UUID_COMPARATOR =
            (n1, n2) -> n1.getUuid().compareTo(n2.getUuid());

//    protected static final Comparator<Resume> NAME_COMPARATOR =
//            (n1, n2) -> n1.getFullName().compareTo(n2.getFullName());

    protected abstract void doSave(Resume resume, Object keyIndexUuid);

    protected abstract void doUpdate(Object keyIndexUuid, Resume resume);

    protected abstract void doDelete(Object keyIndexUuid);

    protected abstract boolean isExist(Object keyIndex);

    protected abstract Object getIndex(String uuid);

    protected abstract Resume doGet(Object keyIndexUuid);

    public void save(Resume resume) {
        Object keyIndexUuid = getIndexIfNotExist(resume.getUuid());
        doSave(resume, keyIndexUuid);
    }

    public void update(Resume resume) {
        Object keyIndexUuid = getIndexIfExist(resume.getUuid());
        doUpdate(keyIndexUuid, resume);
    }

    public Resume get(String uuid) {
        return doGet(getIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        doDelete(getIndexIfExist(uuid));
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