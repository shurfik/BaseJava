package com.basejava;

import com.basejava.model.Resume;
import com.basejava.storage.ListStorage;
import com.basejava.storage.Storage;

import java.util.*;

public class MainCollection {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
//                collection.remove(r);
            }
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection);

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        // Bad!
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("TEST LIST STORAGE");

        Storage storage = new ListStorage();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        Object all = storage.getAll();
        System.out.println(all);
        System.out.println("Размер ListStorage после добавления трех элементов = " + storage.size());

        storage.delete(UUID_2);
        System.out.println(Arrays.toString(storage.getAll()));

        System.out.println("Размер ListStorage после удаления элемента с UUID2 = " + storage.size());


    }
}