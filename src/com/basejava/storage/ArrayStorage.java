package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based com.basejava.storage for Resumes
 */
public class ArrayStorage {

    private final int STORAGE_LIMIT = 10000;

    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("ERROR: ArrayStorage full");
        } else if (isExisting(index)) {
            System.out.println("ERROR: Resume is already exist!");
        } else {
            storage[size++] = r;
            System.out.println("Resume with uuid = \"" + r.getUuid() + "\" successful saved");
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (!isExisting(index)) {
            System.out.println("ERROR: resume with uuid = \"" + r.getUuid() + "\" not found");
        } else {
            storage[index] = r;
            System.out.println("Resume successful updated");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (isExisting(index)) {
            return storage[index];
        }
        System.out.println("ERROR: resume with uuid = \"" + uuid + "\" not found");
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (isExisting(index)) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println("Resume with uuid = \"" + uuid + "\" successful deleted");
        } else {
            System.out.println("ERROR: resume with uuid = \"" + uuid + "\" not found");
        }
    }

    /**
     * @return array, contains only Resumes in com.basejava.storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExisting(int index) {
        if (index != -1){
            System.out.println("Resume with uuid = \"" + storage[index].getUuid() + "\" exist");
            return true;
        }
        System.out.println("Resume not found");
        return false;
    }
}
