import java.util.Scanner;
import java.math.BigInteger;

public class kth_javap_basen {
    /*  
        Labb 3:
            I indata anges först talen N resp. M på första raden, sedan följer talen A och B på varsin egen rad.
            Du kan anta att N och M är positiva heltal, och minst 2 och högst 36, inte är större än Integer.MAX_VALUE.

            Utdata: Ett heltal C angett i bas M (produkten av A och B).

        */


    public static void main (String[] args) {

        Scanner scanObj = new Scanner(System.in);

        // Skriv först bas N och M med mellan slag emellan och radbryt efter
        int N = scanObj.nextInt();
        int M = scanObj.nextInt();
        scanObj.nextLine();

        // Skriv nu heltal A, följt av radbryt
        // A och B definieras för att tillhöra bas N
        BigInteger A = new BigInteger(scanObj.next().trim(), N);    
        // Skriv sist heltal B
        BigInteger B = new BigInteger(scanObj.next().trim(), N);
        scanObj.close();

        String result = javap_basen(N, M, A, B);
        System.out.println(result);

    }

    private static String javap_basen(Integer n, Integer m, BigInteger a, BigInteger b) {
        BigInteger c = a.multiply(b);

        return c.toString(m).toUpperCase();


    }
    
}
