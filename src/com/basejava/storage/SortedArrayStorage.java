package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(int index, Resume r) {
            index = -(index + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;

    }

    @Override
    protected void removeElement(int index) {
            System.arraycopy(storage, index + 1, storage, index, size--);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}