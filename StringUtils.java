package day1;

public class StringUtils {

	public static String normalize(String input) {
		if(input == null || input.isEmpty()) return "";
		String[] words = input.trim().split("\\s+");
		StringBuilder sb = new StringBuilder();
		for(String word: words) {
			sb.append(toTitleCase(word)).append(" ");
		}
		return sb.toString().trim();
	}
		
	public static boolean isPalindrome(String input) {
		if(input == null) return false;
		input = input.replaceAll("\\s+", "").toLowerCase();
		int i =0, j=input.length() - 1;
		while(i<j) {
			if(input.charAt(i)!= input.charAt(j)) return false;
			i++;
			j--;
		}
		return true;
	}
     public static int countOccurences(String text, String word) {
    	 if(text == null || word == null || text.isEmpty() || word.isEmpty()) return 0;
    	 String[] words = text.split("\\s+");
    	 int count = 0;
    	 for (String w: words) {
    		 if(w.equalsIgnoreCase(word)) count++;
    	 }
    	 return count;
     }
     
    public static String toTitleCase(String input) {
		// TODO Auto-generated method stub
    	if(input == null || input.isEmpty()) return "";
    	input = input.toLowerCase();
		return input.substring(0, 1).toUpperCase()+input.substring(1);
	}

}
