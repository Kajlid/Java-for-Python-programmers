import java.math.BigInteger;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Läs in N och M
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Läs in talen A och B
        String A = scanner.next();
        String B = scanner.next();

        // Beräkna produkten C i bas M
        String C = multiplyInBase(A, B, N, M);

        // Skriv ut resultatet
        System.out.println(C);
    }

    // Funktion för att multiplicera två tal i bas N och returnera resultatet i bas M
    private static String multiplyInBase(String A, String B, int baseA, int baseM) {
        BigInteger aBase10 = new BigInteger(A, baseA);
        BigInteger bBase10 = new BigInteger(B, baseA);
        BigInteger cBase10 = aBase10.multiply(bBase10);

        return cBase10.toString(baseM).toUpperCase();
    }
}
