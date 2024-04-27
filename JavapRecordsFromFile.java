import java.util.*;
import java.io.*;

public class JavapRecordsFromFile {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("vegetables.txt"));
        List<GrontRekord> records = new ArrayList<>();
        try {
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {    
            // For every line in file.
            String[] parts = currentLine.split(" ");
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

        }
        
        finally {
            reader.close();
        }
        
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
