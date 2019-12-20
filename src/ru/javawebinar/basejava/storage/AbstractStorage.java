package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        Object keyIndexUuid =  cGetIndexIfNotExist(resume);
        cSave(resume, keyIndexUuid);
    }

    public abstract void cSave(Resume resume, Object keyIndexUuid);

    public void update(Resume resume) {
        Object keyIndexUuid = cGetIndexIfExist(resume);
        cUpdate(keyIndexUuid, resume);
    }

    protected abstract void cUpdate(Object keyIndexUuid, Resume resume);

    public Resume get(String uuid) {
        return pickResume(cGetIndexIfExist(uuid));
    }

    public void delete(String uuid) {
        cDelete(cGetIndexIfExist(uuid));
    }

    protected abstract void cDelete(Object keyIndexUuid);

    private Object cGetIndexIfExist(Resume resume){
        return cGetIndexIfExist(resume.getUuid());
    }

    protected abstract Object cGetIndexIfExist(String uuid);

    private Object cGetIndexIfNotExist(Resume resume){
        return cGetIndexIfNotExist(resume.getUuid());
    }

    protected abstract Object cGetIndexIfNotExist(String uuid);

    protected abstract Resume pickResume(Object keyIndexUuid);
}