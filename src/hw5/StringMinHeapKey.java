package hw5;

public class StringMinHeapKey implements Comparable<StringMinHeapKey> {
    private String key;

    public StringMinHeapKey(String key) {
        this.key = key;
    }

    public int compareTo(StringMinHeapKey o) {
        /*
         * The line below uses the compareTo method in String to compare the 
         * keys. This means "B" has a higher priority than "A".
         * 
         * Fix this so that the keys as specified below form a valid heap in 
         * the given order. I.e. "A" has a higher priority than  "B", etc.
         */
    	//
    	
        return o.key.compareTo(key);
    }

    public static void main(String[] args) {
        String[] keys = new String[] {"A", "B", "C", "D", "E", "F", "G"};
        Integer[] values = new Integer[] {1, 2, 3, 4, 5, 6, 7};

        // Build StringMinHeapKeys from the keys
        StringMinHeapKey[] strMinKeys = new StringMinHeapKey[keys.length];
        for (int i = 0; i < keys.length; i++) {
            strMinKeys[i] = new StringMinHeapKey(keys[i]);
        }

        // Make a heap from the strMinKeys and values
        COMP232ArrayHeap<StringMinHeapKey, Integer> heap = new COMP232ArrayHeap<StringMinHeapKey, Integer>(strMinKeys, values);

        // Print out the values in priority order.
        // When the solution is correct, the values should print in order:
        // 1 Because "A" has the highest priority
        // 2
        // 3 
        // ... etc.
        while(heap.size() > 0) {
            System.out.println(heap.remove());
        }
    }
}
