package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Resume with uuid = " + r.getUuid() + " not exist!");
        }
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            index = -(index + 1);
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        } else {
            System.out.println("ERROR: Resume with uuid = " + r.getUuid() + " already exist!");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ERROR: Resume with uuid = " + uuid + " not exist!");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.arraycopy(storage, index + 1, storage, index, size--);
        } else {
            System.out.println("ERROR: Resume with uuid = " + uuid + " not found!");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}