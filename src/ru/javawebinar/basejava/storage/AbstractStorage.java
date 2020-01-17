package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage <SK> implements Storage {

    protected abstract void doSave(Resume resume, SK keyIndexUuid);

    protected abstract void doUpdate(SK keyIndexUuid, Resume resume);

    protected abstract void doDelete(SK keyIndexUuid);

    protected abstract boolean isExist(SK keyIndex);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume doGet(SK keyIndexUuid);

    protected abstract List<Resume> getResumeList();

    @Override
    public void save(Resume resume) {
        SK keyIndexUuid = getIndexIfNotExist(resume.getUuid());
        doSave(resume, keyIndexUuid);
    }

    @Override
    public void update(Resume resume) {
        SK keyIndexUuid = getIndexIfExist(resume.getUuid());
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

    private SK getIndexIfExist(String uuid) {
        SK keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) return keyIndex;
        else throw new NotExistStorageException(uuid);
    }

    private SK getIndexIfNotExist(String uuid) {
        SK keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) throw new ExistStorageException(uuid);
        else return keyIndex;
    }
}