import java.util.ArrayList;
import java.util.Random;

//an array from which you can take elements in a random order (for shuffling deck)
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