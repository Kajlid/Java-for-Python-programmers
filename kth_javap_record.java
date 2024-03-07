import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class kth_javap_record {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<GrontRekord> records = new ArrayList<>();

        System.out.println("Enter vegetable records (format: <VegetableType> <Country> <Size> <Unit>):");
        System.out.println("Enter 'done' to finish.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("done")) {
                break;
            }
            String[] parts = input.split(" ");
            String vegetableType = parts[0];
            String country = "";
            for (int i = 1; i < parts.length - 2; i++) {
                country += parts[i] + " ";
            }
            country = country.trim(); // Remove leading/trailing whitespace
            int size = Integer.parseInt(parts[parts.length - 2]);
            String unit = parts[parts.length - 1];
            records.add(new GrontRekord(vegetableType, country, size, unit));
        }

        // Sort records
        Collections.sort(records);

        // Print sorted records
        for (GrontRekord record : records) {
            System.out.println(record);
        }

        scanner.close();
    }

}