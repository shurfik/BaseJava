package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage<Map<String, Resume>, String> {

    private final Map<String, Resume> storage;

    public MapStorage() {
        storage = new HashMap<>();
    }

    @Override
    protected String searchKey(String uuid) {
        if (storage.get(uuid) != null){
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean isExist(String key) {
        return key != null;
    }

    @Override
    protected void saveResume(String key, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(String key, Resume r) {
        storage.computeIfPresent(key, (k, v) -> r);
    }

    @Override
    protected void deleteResume(String key, String uuid) {
        storage.remove(key);
    }

    @Override
    protected Resume getResume(String key) {
        return storage.get(key);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Map<String, Resume> getAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
