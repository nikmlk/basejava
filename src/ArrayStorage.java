import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int index = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        ;
        index = 0;
    }

    void save(Resume r) {
        storage[index] = r;
        index = index + 1 == storage.length ? index : index + 1;
    }

    Resume get(String uuid) {
        int index = findIndex(uuid);
        return index == -1 ? null : storage[index];
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            for (int i = index; i < size(); i++) {
                if (i + 1 == size()) {
                    storage[i] = null;
                } else {
                    storage[i] = storage[i + 1];
                }
            }
            this.index = this.index - 1 < 0 ? 0 : this.index - 1;
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return index;
    }
}
