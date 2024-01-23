import java.util.Set;
import java.util.Scanner;
import java.util.TreeSet;

public class test {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        String S = scanObj.nextLine();

        System.out.println(setToString(permutations(S))); 
    }

    private static Set<String> permutations(String s) {
        Set<String> permutationSet = new TreeSet<>(); // nytt set 

        if (s.length() == 0) {
            permutationSet.add("");
            return permutationSet;
        }
        
        // char firstChar = s.charAt(0); // sparar första tecknet
        permutationSet.add(String.valueOf(s.charAt(0)));

        // istället för rekursivt anrop kör vi nestad for-loop:
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);  // för varje char i sträng s
            Set<String> words = new TreeSet<>(); // nya kombinationer/permutationer 

            for (String newStr : permutationSet) {
                System.out.println("newStr:" + newStr);
                for (int j = 0; j < newStr.length(); j++) {
                    words.add(newStr.substring(0, j) + current + newStr.substring(j)); 
                }
                words.add(newStr + current); // add the current character at the end as well
            }

            permutationSet = words;
        }

        return permutationSet;
    }

    // private static String placeraTecken(int i, String str, char f)

    /* 

        private static String placeraTecken(String str, int i, char f) {
        String begin = str.substring(0, i);
        String end = str.substring(i);
        return begin + f + end;
    }

    */

    /* 
    private static String setToString(Set<String> temp) {
        StringBuilder out = new StringBuilder();
        for (String str : temp) {
            out.append(str).append("\n");
        }

        return out.toString();
    }

    */

    private static String setToString(Set<String> temp) {
        String out = "";
        for (String str : temp) {
            out += str + "\n";
          }

        return out;
        
    }
}
