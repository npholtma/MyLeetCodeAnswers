public class LRUCacheDriver {
    public static void main(String[] args) {
        // Test case inputs
        int[][] testInputs = {
            {2},
            {1, 1},
            {2, 2},
            {1},
            {3, 3},
            {2},
            {4, 4},
            {1},
            {3},
            {4}
        };

        LRUCache lruCache = null;
        int capacity = 0;

        for (int[] input : testInputs) {
            String operation = input.length == 1 ? "get" : "put";
            if (operation.equals("put")) {
                int key = input[0];
                int value = input[1];
                System.out.println("put(" + key + ", " + value + ")");
                if (lruCache == null) {
                    capacity = key; // First input is the capacity
                    lruCache = new LRUCache(capacity);
                } else {
                    lruCache.put(key, value);
                }
            } else {
                int key = input[0];
                System.out.print("get(" + key + ") => ");
                int result = lruCache.get(key);
				System.out.println(result);
            }
        }
    }
}
