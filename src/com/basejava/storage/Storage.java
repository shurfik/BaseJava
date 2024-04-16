package com.basejava.storage;

import com.basejava.model.Resume;

public interface Storage<T> {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    T getAll();

    int size();
}