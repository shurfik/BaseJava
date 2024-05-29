package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    protected void saveResume(Object key, Resume r) {
        storage.put((String) key, r);
    }

    @Override
    protected void updateResume(Object key, Resume r) {
        storage.computeIfPresent((String) key, (k, v) -> r);
    }

    @Override
    protected void deleteResume(Object key, String uuid) {
        storage.remove((String) key);
    }

    @Override
    protected Resume getResume(Object key) {
        return storage.get((String) key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection<Resume> values = storage.values();
        return values.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}