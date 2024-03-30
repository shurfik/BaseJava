package com.basejava.storage;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final Resume RESUME_1 = new Resume("uuid1");
    private static final Resume RESUME_2 = new Resume("uuid2");
    private static final Resume RESUME_3 = new Resume("uuid3");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
        Resume[] resumes = storage.getAll();
        Assert.assertArrayEquals(resumes, new Resume[]{});
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume("uuid2");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(newResume.getUuid()));
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Resume[] savedResumes = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(resumes, savedResumes);
    }

    @Test
    public void save() throws Exception {
        final String uuid = "uuid100";
        final Resume newResume = new Resume(uuid);
        storage.save(newResume);
        assertGet(newResume);
        assertSize(4);
        Assert.assertEquals(newResume, storage.get(newResume.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(RESUME_1.getUuid());
        assertSize(2);
        storage.get(RESUME_1.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfNotExist() {
        storage.delete("uuid555");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void sizeOverflowTest() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
                System.out.println(i);
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    private void assertSize(int size){
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume){
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
}