import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = size(); i >= 0; i--) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int indexElementToRemove = -1;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                indexElementToRemove = i;
            }
        }
        if (indexElementToRemove != -1) {
            for (int i = indexElementToRemove + 1; i < size(); i++) {
                storage[i - 1] = storage[i];
            }
            storage[size() - 1] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = storage[i];
        }
        return result;
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                size = i;
                break;
            }
        }
        return size;
    }
}
