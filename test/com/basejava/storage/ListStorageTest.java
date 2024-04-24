package com.basejava.storage;

import com.basejava.model.Resume;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListStorageTest extends AbstractStorageTest<List<Resume>> {

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Test
    public void getAll() {
        List<Resume> list = storage.getAll();
        assertEquals(3, list.size());
        assertEquals(RESUME_1, list.get(0));
        assertEquals(RESUME_2, list.get(1));
        assertEquals(RESUME_3, list.get(2));
    }
}