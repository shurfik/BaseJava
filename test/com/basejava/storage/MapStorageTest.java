package com.basejava.storage;

import com.basejava.model.Resume;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapStorageTest extends AbstractStorageTest<Map<String, Resume>> {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    public void getAll() {
        Map<String, Resume> mapStorage = storage.getAll();
        assertEquals(3, mapStorage.size());
        assertEquals(RESUME_1, mapStorage.get(RESUME_1.getUuid()));
        assertEquals(RESUME_2, mapStorage.get(RESUME_2.getUuid()));
        assertEquals(RESUME_3, mapStorage.get(RESUME_3.getUuid()));
    }
}