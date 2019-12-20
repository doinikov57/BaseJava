package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Exception.ExistStorageException;
import ru.javawebinar.basejava.Exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> storage = new ArrayList<Resume>();

    @Override
    public void clear() {
        storage.clear();
    }

//    @Override
//    public void save(Resume resume) {
//        if (storage.contains(resume)) {
//            throw new ExistStorageException(resume.getUuid());
//        }
//        storage.add(resume);
//    }

    @Override
    public void cSave(Resume resume, Object keyIndexUuid) {
        storage.add(resume);
    }

    //    @Override
//    public void update(Resume resume) {
//        int index = storage.indexOf(resume.getUuid());
//        if (index > -1) {
//            storage.set(index, resume);
//        } else {
//            throw new NotExistStorageException(resume.getUuid());
//        }
//    }

    protected void cUpdate(Object index, Resume resume) {
        storage.set((Integer)index,resume);
    }

    //    @Override
//    public Resume get(String uuid) {
//        int index = getIndex(uuid);
//        if (index > -1) {
//            return storage.get(index);
//        } else {
//            throw new NotExistStorageException(uuid);
//        }
//    }

    @Override
    public Object cGetIndexIfExist(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return index;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Object cGetIndexIfNotExist(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return index;
        } else {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public Resume pickResume(Object index) {
        return storage.get((Integer) index);
    }

//    @Override
//    public void delete(String uuid) {
//        int index = getIndex(uuid);
//        if (index > -1) {
//            storage.remove(index);
//        } else {
//            throw new NotExistStorageException(uuid);
//        }
//    }

    @Override
    protected void cDelete(Object index) {
        storage.remove((Integer)index);
    }

    @Override
    public Resume[] getAll() {
//        Resume[] arrOfResume = new Resume[storage.size()];
//        storage.toArray(arrOfResume);
        return (storage.toArray(new Resume[storage.size()]));
    }

    @Override
    public int size() {
        return storage.size();
    }

    protected int getIndex(String uuid) {
        int index = 0;
        for (Resume r : storage) {
            if (r.getUuid() == uuid) {
                return index;
            }
            index++;
        }
        return -1;
    }
}

