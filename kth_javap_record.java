import java.util.*;

public class kth_javap_record {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<GrontRekord> records = new ArrayList<>();

        // format: <Grönsakstyp (ett ord)> <Namn på landet (ett eller flera ord)> <Storlek (heltal)> <enhet (ett ord)>
        System.out.println("Skriv in listan med grönsaker:");
        System.out.println("Skriv 'klar' för att avsluta.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("klar")) {
                break;
            }
            String[] parts = input.split(" ");
            String type = parts[0];
            String country = "";
            // två sista 'kategorierna' ska vara storlek och enhet
            for (int i = 1; i < parts.length - 2; i++) {
                country += parts[i] + " ";
            }
            country = country.trim();   // Rensa på whitespace
            // eftersom länder kan ha mellanslag emellan
            int size = Integer.parseInt(parts[parts.length - 2]);
            String unit = parts[parts.length - 1];
            records.add(new GrontRekord(type, country, size, unit));
        }


        Collections.sort(records);

        // records.stream().forEach(System.out::print);

        for (GrontRekord record : records) {
            System.out.println(record);
        }

        scanner.close();
    }

}