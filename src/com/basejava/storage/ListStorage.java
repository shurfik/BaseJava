package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<List<Resume>> {

    public ListStorage() {
        storage = new ArrayList<>();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage.add(index, r);
    }

    @Override
    protected void deleteResume(int index, String uuid) {
        storage.remove(index);
    }

    @Override
    protected void saveResume(int index, Resume r) {
        storage.add(r);
    }

    @Override
    public Resume getResume(int index) {
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
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}