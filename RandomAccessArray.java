import java.util.ArrayList;
import java.util.Random;

public class RandomAccessArray<E> extends ArrayList<E> {
    private final Random random = new Random();

    public RandomAccessArray(int initialCapacity) {
        super(initialCapacity);
    }

    public E removeRandom() {
        if(isEmpty()) return null;
        return remove(random.nextInt(size()));
    }
}