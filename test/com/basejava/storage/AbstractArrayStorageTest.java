package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume("uuid2");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(newResume.getUuid()));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Assert.assertArrayEquals(resumes, new Resume[]{new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)});
    }

    @Test
    public void saveIfNotExist() throws Exception {
        Resume newResume = new Resume("uuid100");
        storage.save(newResume);
        Assert.assertEquals(newResume, storage.get(newResume.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() throws Exception {
        Resume newResume = new Resume("uuid1");
        storage.save(newResume);
    }

    @Test
    public void delete() throws Exception {
        storage.delete("uuid1");
        Resume[] resumes = storage.getAll();
        Arrays.sort(resumes);
        Assert.assertArrayEquals(resumes, new Resume[]{new Resume(UUID_2), new Resume(UUID_3)});
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfNotExist() {
        storage.delete("uuid555");
    }

    @Test
    public void get() throws Exception {
        Resume newResume = new Resume("uuid555");
        storage.save(newResume);
        Resume resume = storage.get(newResume.getUuid());
        Assert.assertEquals(newResume, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = AssertionError.class)
    public void sizeOverflowTest() throws Exception {
        int numberOfEmptyElements = 10000 - storage.size();
        try {
            for (int i = 0; i < numberOfEmptyElements + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("ERROR: storage overflow");
        }
    }
}