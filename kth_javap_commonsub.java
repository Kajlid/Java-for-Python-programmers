import java.util.Scanner;
import java.util.HashMap;    // HashMap har konstant tidskomplexitet (O(1)), är snabbare än TreeMap, i alla fall för små inputs (TreeMap har O(log(n)))
import java.io.IOException;
import java.util.Map;

/*
 Labb 2:
    Hitta den oftast förekommande delsträngen i en längre sträng.
    Om flera delsträngar av den angivna längden förekommer flest gånger, ange den delsträng som kommer först alfabetiskt.

    Indata: Ett heltal n (num), på en egen rad, följt av en icke radbryten sträng S.

    Utdata: Den vanligaste förekommande delsträngen d av längd n.

 */

public class kth_javap_commonsub {
    public static void main (String[] args) {

        Scanner scanObj = new Scanner(System.in);   

        // Skriv en siffra:
        int num = Integer.parseInt(scanObj.nextLine().trim());   // Även scanObj.nextInt hade funkat för att läsa in som integer.

        // Skriv en sträng:
        String S = scanObj.nextLine().trim();

        System.out.println(commonSub(num, S));

        scanObj.close();  

    }

    public static String commonSub(Integer num, String s){
        Map<String, Integer> storeSubStrings = new HashMap<>();     // nyckel:String, value:Integer (måste skrivas som objekt-typer)
        
        for (int i = 0; i <= s.length()-num; i++) {
            String d = s.substring(i, i+num);    // slutindex är s.length()
            int numberOfSubstring = storeSubStrings.getOrDefault(d, 0);  // 0 ifall ej förekommit
            storeSubStrings.put(d, numberOfSubstring + 1);   // öka numret med 1 för varje iteration

        }

        Map.Entry<String, Integer> commonEntry = null;   // Key-value par vars key kommer lagra vanligaste delsträngen (d). 

        for (Map.Entry<String, Integer> entry: storeSubStrings.entrySet()){
            if (commonEntry == null || entry.getValue() > commonEntry.getValue() || (entry.getValue().equals(commonEntry.getValue()) && entry.getKey().compareTo(commonEntry.getKey()) < 0)){
                commonEntry = entry;
            }
        }

        return (commonEntry != null)? commonEntry.getKey(): "";    // returnerna vanligaste delsträngen eller tom sträng.

    }


}
