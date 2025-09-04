package day1;

public class ArrayUtils {
	 
    // 1. Sort ascending
    public static int[] sortAscending(int[] arr) {
        if (arr == null) return new int[0];
        int[] result = arr.clone();
        for (int i = 0; i < result.length; i++) {
            for (int j = i + 1; j < result.length; j++) {
                if (result[i] > result[j]) {
                    int temp = result[i];
                    result[i] = result[j];
                    result[j] = temp;
                }
            }
        }
        return result;
    }
 
    // 2. Sort descending
    public static int[] sortDescending(int[] arr) {
        int[] result = sortAscending(arr);
        int n = result.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = result[i];
            result[i] = result[n - i - 1];
            result[n - i - 1] = temp;
        }
        return result;
    }
 
    // 3. Find maximum
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
 
    // 4. Merge two arrays
    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = new int[0];
        if (arr2 == null) arr2 = new int[0];
        int[] merged = new int[arr1.length + arr2.length];
        int k = 0;
        for (int i : arr1) merged[k++] = i;
        for (int i : arr2) merged[k++] = i;
        return merged;
    }
}
