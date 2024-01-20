import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;  

public class kth_javap_permutations {
    public static void main(String[] args){
        Scanner scanObj = new Scanner(System.in);
        String S = scanObj.nextLine();

        System.out.println(setToString(permutations(S)));

    }

    private static Set<String> permutations(String s) {
        Set<String> permutationSet = new HashSet<String>();

        if (s.length() == 0) {
            permutationSet.add("");
            return permutationSet;
        }
        char firstChar = s.charAt(0);    // sparar första tecknet
        String remaining = s.substring(1);
        Set<String> words = permutations(remaining);  // gör funktionen rekursiv, med en ny bokstav som första

        for (String newStr : words) {
            for (int i = 0;i<=newStr.length();i++){
                permutationSet.add(placeraTecken(i, newStr, firstChar));
            }
        }

        return permutationSet;

    }

    private static String placeraTecken(int i, String str, char f) {
        String begin = str.substring(0, i);
        String end = str.substring(i);
        return begin + f + end;
    }

    private static String setToString(Set<String> temp) {
        String out = "";
        for (String str : temp) {
            out += str + "\n";
          }

        return out;
        
    }



}
