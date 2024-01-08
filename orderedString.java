public class orderedString {
    public static void main(String[] args) {
        String S = "123abcABCDabcd1234";
        System.out.println(findLongestSortedSubstring(S)); 
    }

    public static int findLongestSortedSubstring(String s) {

        if (s.isEmpty()) {
            return 0;  // Om strängen är tom returnerar vi 0
        }

        int L = 1;
        String[] parts = s.split(" ");
        
        // Ta bort mellanslaget och sätt ihop strängen igen
        s = String.join("", parts);
        s = s.replaceAll("[^0-9A-Za-z]", ""); // ta bort alla specialtecken

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
        int indexA = order.indexOf(a);
        int indexB = order.indexOf(b);

        if (indexA == -1 || indexB == -1) {  // om tecken som inte finns med i ordningen skrivs med
            return 0; // Ignorera specialtecken genom att returnera 0 istället
        }

        return indexA - indexB;
    }
}
