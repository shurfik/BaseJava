package com.basejava;

import com.basejava.model.Resume;
import com.basejava.storage.MapStorage;

public class MainMap {

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {

        MapStorage mapStorage = new MapStorage();

        mapStorage.save(RESUME_1);
        mapStorage.save(RESUME_2);
        mapStorage.save(RESUME_3);

        System.out.println(mapStorage.getAll());


        System.out.println(mapStorage.size());
        mapStorage.delete(UUID_2);
        System.out.println(mapStorage.size());

        mapStorage.clear();
        System.out.println(mapStorage.size());
        mapStorage.delete(UUID_4);
    }
}
