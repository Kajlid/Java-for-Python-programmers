import java.io.*;
import java.util.*;

// Glöm inte att skriva klar :)

// array av RekordData
// alla giltliga datarader ska läggas i denna array
// Arrays.sort  - även Collections.sort borde funka
public class kth_javap_record {
    // private String[] RekordData;   

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Scanner scanner = new Scanner(System.in);
        List<GrontRekord> records = new ArrayList<>();

        // format: <Grönsakstyp (ett ord)> <Namn på landet (ett eller flera ord)> <Storlek (heltal)> <enhet (ett ord)>
        // System.out.println("Skriv in listan med grönsaker:");
        // System.out.println("Skriv 'klar' för att avsluta.");

        while (true) {
            // String input = scanner.nextLine();   // NoSuchElementException
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
            // eftersom länder kan ha mellanslag emellan
            int size = Integer.parseInt(parts[parts.length - 2]);
            String unit = parts[parts.length - 1];
            records.add(new GrontRekord(type, country, size, unit));
            // records.add("\n");
        }

        // scanner.close();

        Collections.sort(records);

        // records.stream().forEach(System.out::print);

        List<GrontRekord> RekordData = removeDuplicates(records);
        // System.out.println(RekordData);
        StringBuilder utdata = new StringBuilder();
        for (GrontRekord record : RekordData) {
            // System.out.println(record);
            utdata.append(record).append("\n");
        }

        System.out.println(utdata.toString());

        reader.close();   // stäng för att inte läcka resurser.

        // System.out.println(records);

        /*for (GrontRekord record : records) {
            System.out.println(record);
        }*/

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