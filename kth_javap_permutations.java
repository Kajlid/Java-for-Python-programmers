import java.util.Set;
import java.util.Scanner;
import java.util.TreeSet;

/*
 Labb 4:
    Läs in en sträng S, skriv ut en radbruten lista med alla unika permutationer som kan göras av strängen, i alfabetisk ordning.

    Indata: En icke radbryten sträng S, som består av tecken A-Z.
    Utdata: En radbruten lista, sorterad alfabetiskt. En permutation per rad.
 */

public class kth_javap_permutations {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);
        String S = scanObj.nextLine();

        System.out.println(setToString(permutations(S))); 
    }

    private static Set<String> permutations(String s) {
        Set<String> permutationSet = new TreeSet<>();    // ordning i setet spelar roll

        if (s.length() == 0) {
            // Lägg till en tom sträng om ingen sträng skickats in.
            permutationSet.add("");
            return permutationSet;
        }
        
        permutationSet.add(String.valueOf(s.charAt(0)));    // första karaktären i strängen

        // istället för rekursivt anrop kör vi nestad for-loop:
        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);   // för varje char i sträng s
            Set<String> words = new TreeSet<>(); // nya kombinationer/permutationer 

            for (String newStr : permutationSet) {
                for (int j = 0; j < newStr.length(); j++) {
                    words.add(newStr.substring(0, j) + current + newStr.substring(j)); 
                }
                words.add(newStr + current); 
            }

            permutationSet = words;
        }

        return permutationSet;
    }

    
    private static String setToString(Set<String> temp) {
        // Gör om setet till en sträng med radbryt för output.
        // StringBuilder är som strings, fast mutable, så de går att ändra i.
        StringBuilder out = new StringBuilder();
        for (String str : temp) {
            out.append(str).append("\n");
        }

        return out.toString();
    }

}
