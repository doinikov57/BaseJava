package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage <String> {

    private final static File RESUME_DIRECTORY = new File("C:\\Users\\doinikov\\basejava\\ResumeDir");

    @Override
    protected void doSave(Resume resume, String fileName) {
        fileWrite(resume, fileName);
    }

    protected abstract void fileWrite(Resume resume, String fileName);

    @Override
    protected void doUpdate(String fileName, Resume resume) {
        fileDelete(fileName);
        fileWrite(resume, fileName);
    }

    protected abstract void fileDelete(String fileName);

    @Override
    protected void doDelete(String fileName) {
        fileDelete(fileName);
    }

    @Override
    protected boolean isExist(String fileName) {
        File file = new File(RESUME_DIRECTORY, fileName);
        return (file.exists() && file.isFile());
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected Resume doGet(String fileName) {
        return readResume(fileName);
    }

    protected abstract Resume readResume(String fileName);

    @Override
    protected List<Resume> getResumeList() {
        List<Resume> list = new ArrayList<>();
        for (String fileName : RESUME_DIRECTORY.list()) {
            list.add(readResume(fileName));
        }
        return list;
    }

    @Override
    public void clear() {
        for (String fileName : RESUME_DIRECTORY.list()) {
            (new File(fileName)).delete();
        }

    }

    @Override
    public int size() {
        int size = 0;
        for (String fileName : RESUME_DIRECTORY.list()) {
            size++;
        }
        return size;
    }
}