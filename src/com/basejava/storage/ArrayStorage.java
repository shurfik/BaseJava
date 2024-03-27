package com.basejava.storage;

import com.basejava.model.Resume;

/**
 * Array based com.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void addElement(int index, Resume r) {
            storage[size] = r;

    }

    public void removeElement(int index) {
            storage[index] = storage[size - 1];
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
