package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    public void update(Resume r) {
        Object searchKey = getExistingSearchKey(r);
        updateResume(searchKey, r);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistingSearchKey(r);
        saveResume(searchKey, r);
    }

    public Resume get(String uuid) {
        Resume resume = new Resume(uuid);
        Object searchKey = getExistingSearchKey(resume);
        return getResume(searchKey);
    }

    public void delete(String uuid) {
        Resume resume = new Resume(uuid);
        Object searchKey = getExistingSearchKey(resume);
        deleteResume(searchKey, uuid);
    }

    private Object getExistingSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object key);

    protected abstract void saveResume(Object key, Resume r);

    protected abstract void updateResume(Object key, Resume r);

    protected abstract void deleteResume(Object key, String uuid);

    protected abstract Resume getResume(Object key);
}