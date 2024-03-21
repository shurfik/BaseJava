package com.basejava;

import com.basejava.model.Resume;
import com.basejava.storage.SortedArrayStorage;

/**
 * Test for your com.basejava.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid20");
        Resume r3 = new Resume();
        r3.setUuid("uuid30");
        Resume r5 = new Resume();
        r5.setUuid("uuid17");
        Resume r6 = new Resume();
        r6.setUuid("uuid28");
        Resume r7 = new Resume();
        r7.setUuid("uuid45");

        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r7);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        Resume r4 = new Resume();
        r4.setUuid("uuid3");
        ARRAY_STORAGE.update(r4);

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r5.getUuid());
        printAll();
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
