import java.io.*;
import java.util.*;

// Glöm inte att skriva klar!!!


public class kth_javap_record {   
    public static void main(String[] args) throws IOException {
        // Måste kunna hantera IOException för BufferedReader.
        // InputStreamReader konverterar input stream av bytes till characters för BufferedReader.
        // BufferedReader snabbare än scanner, parsar inte datan utan läser endast characters.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<GrontRekord> records = new ArrayList<>();

        // format: <Grönsakstyp (ett ord)> <Namn på landet (ett eller flera ord)> <Storlek (heltal)> <enhet (ett ord)>

        while (true) {
            String input = reader.readLine();
            if (input == null || input.equals("klar")) {
                break;
            }
            String[] parts = input.split(" ");
            String type = parts[0];
            String country = "";
            // två sista 'kategorierna' ska vara storlek och enhet
            for (int i = 1; i < parts.length - 2; i++) {
                country += parts[i] + " ";
            }
            country = country.trim();   // Rensa på omringande whitespace
            int size = Integer.parseInt(parts[parts.length - 2]);
            String unit = parts[parts.length - 1];
            records.add(new GrontRekord(type, country, size, unit));
        }

        Collections.sort(records);

        List<GrontRekord> RekordData = removeDuplicates(records);
      
        StringBuilder utdata = new StringBuilder();
        for (GrontRekord record : RekordData) {
            utdata.append(record).append("\n");
        }

        System.out.println(utdata.toString());

        reader.close();   // stäng för att inte läcka resurser.

    }

    private static List<GrontRekord> removeDuplicates(List<GrontRekord> records) {
        List<GrontRekord> RekordData = new ArrayList<>();;  // output, den sorterade listan
        Set<String> combinationSet = new HashSet<>(); // för att hålla reda på kombinationen land och grönsak
        for (GrontRekord record : records) {
            // nyckel för varje kombination av typ och land
            String kombination = record.vegetableType + "-" + record.country;

            // om kombinations-"nyckeln" inte redan finns
            if (!combinationSet.contains(kombination)) {
                combinationSet.add(kombination);
                RekordData.add(record);
            }
            
        }

        return RekordData;
    }

}