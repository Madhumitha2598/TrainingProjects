package Recursion;

public class Recursion {

	public static void main(String[] args) {
        // (A) Directory Depth Finder
        String[] paths = {
            "/home/user/docs",
            "/home/user/docs/reports",
            "/home/user/music"
        };
 
        System.out.println("Directory Depth (Recursive): " +
            DirectoryDepthFinder.maxDepthRecursive(paths));
        System.out.println("Directory Depth (Iterative): " +
            DirectoryDepthFinder.maxDepthIterative(paths));
 
        // (B) Word Occurrence Counter
        String paragraph = "Java is powerful. java is portable. I love JAVA programming.";
        String word = "java";
 
        System.out.println("Occurrences of \"" + word + "\" (Recursive): " +
            WordCounter.countRecursive(paragraph, word, 0));
        System.out.println("Occurrences of \"" + word + "\" (Iterative): " +
            WordCounter.countIterative(paragraph, word));
 
        // (C) Customer Queue Simulation
        int[] customers = {101, 102, 999, 103};
 
        System.out.println("\nQueue Processing Iterative:");
        CustomerQueue.processIterative(customers);
 
        System.out.println("\nQueue Processing Recursive:");
        CustomerQueue.processRecursive(customers, 0);
 
        // (D) Nested Structure Sum (limited depth example)
        Object[] nested = {1, new Object[]{2, 3}, new Object[]{4, new Object[]{5}}};
 
        System.out.println("\nNested Structure Sum (Recursive): " +
            NestedSum.sumRecursive(nested));
        System.out.println("Nested Structure Sum (Iterative): " +
            NestedSum.sumIterative(nested));
    }
}
 
// ========== (A) Directory Depth Finder ==========
class DirectoryDepthFinder {
    // Recursive
    public static int maxDepthRecursive(String[] paths) {
        int max = 0;
        for (String path : paths) {
            max = Math.max(max, depthHelper(path, 0));
        }
        return max;
    }
 
    private static int depthHelper(String path, int index) {
        if (index >= path.length()) return 0;
        int nextSlash = path.indexOf('/', index + 1);
        if (nextSlash == -1) return 1;
        return 1 + depthHelper(path, nextSlash);
    }
 
    // Iterative
    public static int maxDepthIterative(String[] paths) {
        int max = 0;
        for (String path : paths) {
            String[] parts = path.split("/");
            max = Math.max(max, parts.length - 1);
        }
        return max;
    }
}
 
// ========== (B) Word Occurrence Counter ==========
class WordCounter {
    // Recursive
    public static int countRecursive(String text, String word, int index) {
        if (index > text.length() - word.length()) return 0;
        if (equalsIgnoreCase(text.substring(index, index + word.length()), word)) {
            return 1 + countRecursive(text, word, index + 1);
        }
        return countRecursive(text, word, index + 1);
    }
 
    // Iterative
    public static int countIterative(String text, String word) {
        String[] words = text.split("\\W+");
        int count = 0;
        for (String w : words) {
            // Enhanced switch for case-insensitive compare
            switch (w.toLowerCase()) {
                case "java" -> {
                    if (word.equalsIgnoreCase("java")) count++;
                }
                default -> {}
            }
        }
        return count;
    }
 
    private static boolean equalsIgnoreCase(String a, String b) {
        return a.equalsIgnoreCase(b);
    }
}
 
// ========== (C) Customer Queue Simulation ==========
class CustomerQueue {
    // Iterative with labeled loop
    public static void processIterative(int[] customers) {
        outer:
        for (int id : customers) {
            if (id == 999) {
                System.out.println("Found VIP, stopping service.");
                break outer;
            }
            System.out.println("Serving ID: " + id);
        }
    }
 
    // Recursive
    public static void processRecursive(int[] customers, int index) {
        if (index >= customers.length) return;
        if (customers[index] == 999) {
            System.out.println("Found VIP, stopping service.");
            return;
        }
        System.out.println("Serving ID: " + customers[index]);
        processRecursive(customers, index + 1);
    }
}
 
// ========== (D) Nested Structure Sum ==========
class NestedSum {
    // Recursive
    public static int sumRecursive(Object[] arr) {
        int sum = 0;
        for (Object obj : arr) {
            if (obj instanceof Integer) sum += (int) obj;
            else if (obj instanceof Object[]) sum += sumRecursive((Object[]) obj);
        }
        return sum;
    }
 
    // Iterative (assume limited depth)
    public static int sumIterative(Object[] arr) {
        int sum = 0;
        for (Object obj : arr) {
            if (obj instanceof Integer) {
                sum += (int) obj;
            } else if (obj instanceof Object[]) {
                for (Object inner : (Object[]) obj) {
                    if (inner instanceof Integer) sum += (int) inner;
                    else if (inner instanceof Object[]) {
                        for (Object deep : (Object[]) inner) {
                            sum += (int) deep;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
