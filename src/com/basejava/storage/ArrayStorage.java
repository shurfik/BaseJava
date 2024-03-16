package com.basejava.storage;

import com.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based com.basejava.storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];

    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("ERROR: ArrayStorage full");
        } else if (resumeIsPresent(r.getUuid())) {
            System.out.println("ERROR: Resume is already exist!");
        } else {
            storage[size++] = r;
            System.out.println("Resume with uuid = \"" + r.getUuid() + "\" successful saved");
        }
    }

    public void update(Resume r) {
        if (!resumeIsPresent(r.getUuid())) {
            System.out.println("ERROR: resume with uuid = \"" + r.getUuid() + "\" not found");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(r.getUuid())) {
                    storage[i] = r;
                }
            }
            System.out.println("Resume successful updated");
        }
    }

    public Resume get(String uuid) {
        if (resumeIsPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        System.out.println("ERROR: resume with uuid = \"" + uuid + "\" not found");
        return null;
    }

    public void delete(String uuid) {
        if (resumeIsPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    System.out.println("Resume with uuid = \"" + uuid + "\" successful deleted");
                    break;
                }
            }
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

    private boolean resumeIsPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }
}
