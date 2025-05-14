import java.util.ArrayList;
import java.util.Random;

public class RandomAccessArray<E> extends ArrayList<E> {
    private final Random random = new Random();

    public RandomAccessArray(int initalCapacity) {
        super(initialCapacity);
    }

    public E removeRandom() {
        return remove(random.nextInt(size()));
    }
}