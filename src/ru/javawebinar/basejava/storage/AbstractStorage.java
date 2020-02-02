package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage <SK> implements Storage {

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> getResumeList();

    @Override
    public void save(Resume resume) {
        SK searchKey = getKeyIfNotExist(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public void update(Resume resume) {
        SK searchKey = getKeyIfExist(resume.getUuid());
        doUpdate(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        return doGet(getKeyIfExist(uuid));
    }

    @Override
    public void delete(String uuid) {
        doDelete(getKeyIfExist(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getResumeList();
        Collections.sort(list);
        return list;
    }

    private SK getKeyIfExist(String uuid) {
        SK keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) return keyIndex;
        else throw new NotExistStorageException(uuid);
    }

    private SK getKeyIfNotExist(String uuid) {
        SK keyIndex = getSearchKey(uuid);
        if (isExist(keyIndex)) throw new ExistStorageException(uuid);
        else return keyIndex;
    }

}