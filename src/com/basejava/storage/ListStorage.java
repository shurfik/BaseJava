package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListStorage extends AbstractStorage<List<Resume>, Integer> {

    private final List<Resume> storage;

    public ListStorage() {
        storage = new ArrayList<>();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(Integer index, Resume r) {
        storage.add(index, r);
    }

    @Override
    protected void deleteResume(Integer index, String uuid) {
        storage.remove(index.intValue());
    }

    @Override
    protected void saveResume(Integer index, Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getResume(Integer index) {
        return storage.get(index);
    }

    public List<Resume> getAll() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer searchKey(String uuid) {
        Integer key = null;
        for (int i = 0; i < storage.size(); i++) {
            if (Objects.equals(storage.get(i).getUuid(), uuid)){
                key = i;
                break;
            }
        }
        return key;
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }
}