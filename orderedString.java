public class orderedString {
    public static void main(String[] args) {
        String S = "123abcABCDabcd1234";
        System.out.println(findLongestSortedSubstring(S)); 
    }

    public static int findLongestSortedSubstring(String s) {
        int L = 1;
        int currentSubstring = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) >= s.charAt(i - 1)) {
                currentSubstring++;
            } else {
                L = Math.max(L, currentSubstring);
                currentSubstring = 1;
            }
        }
        return Math.max(L, currentSubstring);
    }
}
