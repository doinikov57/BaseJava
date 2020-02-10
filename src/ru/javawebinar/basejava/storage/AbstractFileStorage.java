package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class  AbstractFileStorage extends AbstractStorage<File> {

    private final File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "Directory could not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() +
                    " is not a directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() +
                    "directory is not readable/writable");
        }
        this.directory = directory;
    }

    protected abstract void writeFile(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume readFile(InputStream is) throws IOException;

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            if (!file.createNewFile()){
                throw new StorageException("File create error", resume.getUuid());
            }
        } catch (IOException e) {
            throw new StorageException(" I/O error", resume.getUuid(), e);
        }
        doUpdate(file, resume);
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        try {
            writeFile(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory,uuid);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return readFile (new BufferedInputStream (new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getResumeList() {
        File[] files = checkDir();
        List<Resume> list = new ArrayList<>();
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = checkDir();
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        return checkDir().length;
    }

    private File[] checkDir () {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        return files;
    }
}