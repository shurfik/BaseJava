package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage;

    public ListStorage() {
        storage = new ArrayList<>();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.add((Integer) index, r);
    }

    @Override
    protected void deleteResume(Object index, String uuid) {
        storage.remove((int) index);
    }

    @Override
    protected void saveResume(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getResume(Object index) {
        return storage.get((Integer) index);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int key = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)) {
                key = i;
                break;
            }
        }
        return key;
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }
}