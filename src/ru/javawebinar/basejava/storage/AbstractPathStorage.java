package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    protected abstract void writeFile(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume readFile(InputStream is) throws IOException;

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException(" I/O error", resume.getUuid(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            writeFile(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File delete error ", path.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return readFile(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> getResumeList() {
        return checkDir().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        checkDir().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) checkDir().count();
    }

    private Stream<Path> checkDir() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory/path read error", null, e);
        }
    }
}