import java.util.Scanner;
import java.util.HashMap;    // HashMap is faster than TreeMap, average time complexity of O(1), constant (behövs för Kattis)
import java.io.IOException;
import java.util.Map;

public class kth_javap_commonsub {
    public static void main (String[] args) throws IOException {

        Scanner scanObj = new Scanner(System.in);   

        System.out.println("Skriv en siffra:");
        int num = Integer.parseInt(scanObj.nextLine().trim());   // assuming user input to be a string can provide better usability

        System.out.println("Skriv en sträng:");
        String S = scanObj.nextLine().trim();

        System.out.println(String.format("Vanligaste delsträngen på längd %d är %s",num, commonSub(num, S)));

        scanObj.close();  // good to close scanner to avoid memory leak

    }

    public static String commonSub(Integer num, String s){


        Map<String, Integer> storeSubStrings = new HashMap<>();

        for (int i = 0; i <= s.length()-num; i++) {
            String d = s.substring(i, i+num);
            int numberOfSubstring = storeSubStrings.getOrDefault(d, 0);  // default is zero to avoid null values
            storeSubStrings.put(d, numberOfSubstring + 1);   // increase the number with 1 every time we have one.

        }

        Map.Entry<String, Integer> commonEntry = null;   // this value will store the most common sub string entry tuple.

        for (Map.Entry<String, Integer> entry: storeSubStrings.entrySet()){
            // if commonEntry still is null, or if it is smaller than current val or if 2 have same values (antal), but alphabetical order
            if (commonEntry == null || entry.getValue() > commonEntry.getValue() || (entry.getValue().equals(commonEntry.getValue()) && entry.getKey().compareTo(commonEntry.getKey()) < 0)){
                commonEntry = entry;
            }
        }

        return (commonEntry != null)? commonEntry.getKey(): "";    // return only substring (key)


    }


}
