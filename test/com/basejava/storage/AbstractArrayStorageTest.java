package com.basejava.storage;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(AbstractArrayStorage storage) {
        super(storage);
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        System.out.println(Arrays.toString(array));
        assertEquals(3, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}