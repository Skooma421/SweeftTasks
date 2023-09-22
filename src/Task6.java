import java.util.Iterator;
import java.util.LinkedList;

public class Task6 <T> implements Iterable <T> {
    private static final int DEFAULT_CAPACITY = 17;
    private static final double LOAD_FACTOR = 0.75;
    private LinkedList<T>[] buckets;
    private int size;

    public Task6() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int hash(T key) {
        if (key == null) return 0;
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    public void add(T key) {
        if ((double)size / buckets.length >= LOAD_FACTOR) {
            resize();
        }
        int index = hash(key);
        LinkedList<T> bucket = buckets[index];
        if (!bucket.contains(key)) {
            bucket.add(key);
            size++;
        }
    }

    public void remove(T key) {
        int index = hash(key);
        LinkedList<T> bucket = buckets[index];
        if (bucket.contains(key)) {
            bucket.remove(key);
            size--;
        }
    }

    public boolean contains(T key) {
        int index = hash(key);
        LinkedList<T> bucket = buckets[index];
        return bucket.contains(key);
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i].clear();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        LinkedList<T>[] newBuckets = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        for (LinkedList<T> bucket : buckets) {
            for (T key : bucket) {
                int newIndex = hash(key);
                newBuckets[newIndex].add(key);
            }
        }
        buckets = newBuckets;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = -1;
            private Iterator<T> currentBucketIterator = null;

            @Override
            public boolean hasNext() {
                if (currentBucketIterator != null && currentBucketIterator.hasNext()) {
                    return true;
                }

                for (int i = currentIndex + 1; i < buckets.length; i++) {
                    if (!buckets[i].isEmpty()) {
                        currentIndex = i;
                        currentBucketIterator = buckets[i].iterator();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return currentBucketIterator.next();
            }
        };
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (T key : this) {
            array[index++] = key;
        }
        return array;
    }

    public static void main(String[] args) {
        Task6<Integer> set = new Task6<>();
        set.add(1);
        set.add(2);
        set.add(3);

        System.out.println("Set contains 2: " + set.contains(2));
        System.out.println("Set size: " + set.size());

        set.remove(2);

        System.out.println("Set contains 2: " + set.contains(2));
        System.out.println("Set size: " + set.size());
    }
}
