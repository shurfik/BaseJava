package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

public abstract class AbstractStorage<T, K> implements Storage<T> {

    protected abstract K searchKey(String uuid);

    public void update(Resume r) {
        K key = getExistingSearchKey(r);
        updateResume(key, r);
    }

    public void save(Resume r) {
        K key = getNotExistingSearchKey(r);
        saveResume(key, r);
    }

    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        K key = getExistingSearchKey(resume);
        return getResume(key);
    }

    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        K key = getExistingSearchKey(resume);
        deleteResume(key, uuid);
    }

    private K getExistingSearchKey(Resume resume) {
        K key = searchKey(resume.getUuid());
        if (!isExist(key)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        return key;
    }

    private K getNotExistingSearchKey(Resume resume) {
        K key = searchKey(resume.getUuid());
        if (isExist(key)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return key;
    }

    protected abstract boolean isExist(K key);

    protected abstract void saveResume(K key, Resume r);

    protected abstract void updateResume(K key, Resume r);

    protected abstract void deleteResume(K key, String uuid);

    protected abstract Resume getResume(K key);
}