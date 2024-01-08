public class orderedString {
    public static void main(String[] args) {
        String S = "DCBABCBA";
        System.out.println(findLongestSortedSubstring(S)); 
    }

    public static int findLongestSortedSubstring(String s) {
        int L = 1;
        int currentSubstring = 1;
        for (int i = 1; i < s.length(); i++) {
            if (compareChars(s.charAt(i), s.charAt(i - 1)) >= 0) {
                currentSubstring++;
            } else {
                L = Math.max(L, currentSubstring);
                currentSubstring = 1;
            }
        }
        return Math.max(L, currentSubstring);
    }

    private static int compareChars(char a, char b) {
        String order = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return order.indexOf(a) - order.indexOf(b);
    }
}
