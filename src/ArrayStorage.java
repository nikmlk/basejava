import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int index = -1;

    void clear() {
        storage = new Resume[10000];
        index = -1;
    }

    void save(Resume r) {
        index = index + 1 == storage.length ? index : index + 1;
        storage[index] = r;
    }

    Resume get(String uuid) {
        int endRange = index + 1;
        return Arrays.stream(Arrays.copyOf(storage, endRange))
                .filter(resume -> resume.uuid.equals(uuid))
                .findFirst()
                .orElse(null);
    }

    void delete(String uuid) {
        int ind = findIndex(uuid);
        if (ind != -1){
            for (int i = ind; i < size(); i++) {
                if (i + 1 == size()){
                    storage[i] = null;
                }else {
                    storage[i] = storage[i + 1];
                }
            }
            index = index - 1;
        }
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index + 1);
    }

    int size() {
        return index + 1;
    }
}
