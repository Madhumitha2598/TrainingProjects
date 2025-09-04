package day1;

public class Main {

	public static void main(String[] args) {
		
		String messy = " java utility LAB ";
		System.out.println("Normalized: "+StringUtils.normalize(messy));
		
		String palindrome = "level";
		System.out.println("Is Palindrome (level): "+ StringUtils.isPalindrome(palindrome));
		
		String sentence = "java is java and java rocks";
		System.out.println("Occurences of 'java': "+StringUtils.countOccurences(sentence, "java"));
		
		
		//Array Utils
		
		int[] arr = {10, 50, 5, 40};
		int[] sorted = ArrayUtils.sortAscending(arr);
		System.out.print("Sorted Ascending: ");
		for(int num:sorted) System.out.print(num+ " ");
		System.out.println();
		
		int[] arr1 = {11, 12};
		int[] arr2 = {21, 22};
		int[] merged = ArrayUtils.merge(arr1, arr2);
		System.out.print("Merged Array: ");
		for(int num:merged) System.out.print(num+ " ");
		System.out.println();

	}

}
